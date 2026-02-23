package dev.donmanuel.rememberme.data.network

import io.ktor.client.*

expect fun createPlatformHttpClient(): HttpClient
