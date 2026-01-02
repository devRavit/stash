package com.ravit.stash.infrastructure.extension

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend inline fun <reified T> HttpClient.post(
    url: String,
    requestBody: Any,
): T {
    val response =
        this.post(url) {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    return response.body<T>()
}
