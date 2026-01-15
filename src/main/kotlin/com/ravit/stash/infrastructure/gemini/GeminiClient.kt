package com.ravit.stash.infrastructure.gemini

import com.ravit.stash.infrastructure.gemini.exception.GeminiException
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
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
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
        val contents =
            listOf(
                GeminiRequestContent(
                    parts = listOf(GeminiRequestPart(text = content)),
                ),
            )
        return generateContentWithHistory(model, contents)
    }

    suspend fun generateContentWithHistory(
        model: String,
        contents: List<GeminiRequestContent>,
    ): String {
        val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent"

        val requestBody = GeminiRequest(contents = contents)

        logger.debug("Sending request to Gemini API: model=$model, messageCount=${contents.size}")
        val response =
            httpClient.post(url) {
                header("x-goog-api-key", aiProperties.key)
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }
        logger.debug("Received response from Gemini API with status: ${response.status}")

        if (response.status == HttpStatusCode.TooManyRequests) {
            throw GeminiException.RateLimitExceededException()
        }

        if (!response.status.isSuccess()) {
            throw GeminiException.ApiException("Gemini API error: ${response.status}")
        }

        val geminiResponse = response.body<GeminiResponse>()

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
