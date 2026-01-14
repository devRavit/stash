package com.ravit.stash.controller.externals.ai

import com.ravit.stash.ai.model.command.AiChatCommand
import com.ravit.stash.ai.service.AiService
import com.ravit.stash.controller.externals.ai.request.AiChatExternalRequest
import com.ravit.stash.controller.externals.ai.response.AiChatExternalResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/externals/ai")
class AiExternalController(
    private val aiService: AiService,
) {
    @PostMapping("/chat")
    suspend fun chat(
        @RequestBody request: AiChatExternalRequest,
    ): AiChatExternalResponse {
        val command = AiChatCommand(message = request.message)
        val response = aiService.chat(command)
        return AiChatExternalResponse(response = response)
    }
}
