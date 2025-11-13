package org.thiha.thant.sin.foa.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.thiha.thant.sin.foa.core.utils.BASE_URL
import kotlin.time.Duration.Companion.seconds

object HttpClientProvider {
    val httpClient by lazy {
        HttpClient {
            defaultRequest {
                url(BASE_URL)
                header(HttpHeaders.Accept, ContentType.Application.Json)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        prettyPrint = true
                    }
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 30.seconds.inWholeMilliseconds
                socketTimeoutMillis = 30.seconds.inWholeMilliseconds
                requestTimeoutMillis = 30.seconds.inWholeMilliseconds
            }
        }
    }
}