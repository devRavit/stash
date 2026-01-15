package com.ravit.stash.controller.externals.ai

import com.ravit.stash.ai.model.command.AiChatCommand
import com.ravit.stash.ai.service.AiService
import com.ravit.stash.controller.externals.ai.request.AiChatExternalRequest
import com.ravit.stash.controller.externals.ai.response.AiChatExternalResponse
import com.ravit.stash.infrastructure.gemini.exception.GeminiException
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/externals/ai")
class AiExternalController(
    private val aiService: AiService,
) {
    private val logger = LoggerFactory.getLogger(AiExternalController::class.java)

    @PostMapping("/chat")
    suspend fun chat(
        @RequestBody request: AiChatExternalRequest,
    ): AiChatExternalResponse {
        val command =
            AiChatCommand(
                clientId = request.clientId,
                message = request.message,
            )
        return try {
            val response = aiService.chat(command)
            AiChatExternalResponse(response = response)
        } catch (e: GeminiException.RateLimitExceededException) {
            logger.warn("Rate limit exceeded for clientId: ${request.clientId}")
            AiChatExternalResponse(
                response = "오늘의 AI 토큰이 모두 소진되었어요 😢\n다음에 다시 와주세요!",
                isError = true,
            )
        } catch (e: GeminiException) {
            logger.error("Gemini API error for clientId: ${request.clientId}", e)
            AiChatExternalResponse(
                response = "AI 서비스에 문제가 발생했어요.\n잠시 후 다시 시도해주세요.",
                isError = true,
            )
        }
    }
}
