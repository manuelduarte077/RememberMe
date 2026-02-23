package dev.donmanuel.rememberme.data.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        expectSuccess = false
    }
}
