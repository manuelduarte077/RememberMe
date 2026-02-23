package dev.donmanuel.rememberme.data.remote

import dev.donmanuel.rememberme.data.model.BirthdaySummaryDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*

private const val apiKeyHeaderName = "X-API-Key"

class RememberMeRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun listBirthdays(baseUrl: String, apiKey: String): List<BirthdaySummaryDto> {
        val normalizedBaseUrl = baseUrl.trim().trimEnd('/')
        val response = httpClient.get("$normalizedBaseUrl/api/v1/birthdays") {
            header(apiKeyHeaderName, apiKey)
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        val body = response.bodyAsText()
        if (!response.status.isSuccess()) {
            val errorMessage = parseErrorMessage(body)
            throw ApiException(errorMessage ?: "Error ${response.status.value} al consultar cumpleaños.")
        }

        return parseBirthdays(body)
    }

    private fun parseBirthdays(rawJson: String): List<BirthdaySummaryDto> {
        val root = Json.parseToJsonElement(rawJson) as? JsonArray
            ?: throw ApiException("Respuesta inesperada del servidor al listar cumpleaños.")

        return root.map { item ->
            val obj = item as? JsonObject
                ?: throw ApiException("Respuesta inesperada del servidor al listar cumpleaños.")

            BirthdaySummaryDto(
                id = obj.string("id"),
                fullName = obj.string("fullName"),
                dateOfBirth = obj.string("dateOfBirth"),
                nextBirthdayDate = obj.string("nextBirthdayDate"),
                daysUntilBirthday = obj.int("daysUntilBirthday"),
                nextBirthdayYearType = obj.string("nextBirthdayYearType"),
                photoPath = obj.nullableString("photoPath")
            )
        }
    }

    private fun parseErrorMessage(rawJson: String): String? {
        return runCatching {
            Json.parseToJsonElement(rawJson)
                .jsonObject["error"]
                ?.jsonPrimitive
                ?.contentOrNull
        }.getOrNull()
    }
}

private fun JsonObject.string(key: String): String {
    return this[key]?.jsonPrimitive?.contentOrNull.orEmpty()
}

private fun JsonObject.nullableString(key: String): String? {
    return this[key]?.jsonPrimitive?.contentOrNull
}

private fun JsonObject.int(key: String): Int {
    return this[key]?.jsonPrimitive?.intOrNull ?: 0
}
