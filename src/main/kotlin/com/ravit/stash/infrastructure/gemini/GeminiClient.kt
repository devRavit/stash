package com.ravit.stash.infrastructure.gemini

import com.ravit.stash.infrastructure.gemini.request.GeminiRequest
import com.ravit.stash.infrastructure.gemini.request.GeminiRequestContent
import com.ravit.stash.infrastructure.gemini.request.GeminiRequestPart
import com.ravit.stash.infrastructure.gemini.response.GeminiResponse
import com.ravit.stash.property.AiProperties
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GeminiClient(
    private val httpClient: HttpClient,
    private val aiProperties: AiProperties,
) {
    private val logger = LoggerFactory.getLogger(GeminiClient::class.java)

    suspend fun generateContent(
        model: String,
        content: String,
    ): String {
        val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent"

        val requestBody =
            GeminiRequest(
                contents =
                    listOf(
                        GeminiRequestContent(
                            parts = listOf(GeminiRequestPart(text = content)),
                        ),
                    ),
            )

        logger.debug("Sending request to Gemini API: model=$model")
        val response =
            httpClient.post(url) {
                header("x-goog-api-key", aiProperties.key)
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }
        val geminiResponse = response.body<GeminiResponse>()
        logger.debug("Received response from Gemini API with status: ${response.status}")

        return geminiResponse
            .candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            .orEmpty()
    }
}
