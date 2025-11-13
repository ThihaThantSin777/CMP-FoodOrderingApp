package org.thiha.thant.sin.foa.core.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.JsonConvertException

suspend inline fun <reified T> transformResult(httpResponse: HttpResponse): T {
    val statusCode = httpResponse.status.value;
    when (statusCode) {
        in 200..299 -> {
            return httpResponse.body<T>()
        }

        else -> {
            try {
                val errorResponse = httpResponse.body<FoodOrderError>()
                throw Exception(errorResponse.error)
            } catch (_: JsonConvertException) {
                throw Exception(httpResponse.bodyAsText())
            }

        }
    }
}