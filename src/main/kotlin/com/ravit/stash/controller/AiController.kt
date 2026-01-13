package com.ravit.stash.controller

import com.ravit.stash.ai.dto.AiChatRequest
import com.ravit.stash.ai.dto.AiChatResponse
import com.ravit.stash.ai.service.AiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ai")
class AiController(
    private val aiService: AiService,
) {
    @PostMapping("/chat")
    suspend fun chat(
        @RequestBody request: AiChatRequest,
    ): AiChatResponse {
        val response = aiService.chat(request.message)
        return AiChatResponse(response = response)
    }
}
