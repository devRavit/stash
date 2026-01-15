package com.ravit.stash.controller.externals.session

import com.ravit.stash.controller.externals.session.request.SessionExternalRequest
import com.ravit.stash.controller.externals.session.response.ChatMessageExternalResponse
import com.ravit.stash.controller.externals.session.response.SessionExternalResponse
import com.ravit.stash.domain.session.service.ChatSessionService
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/externals/sessions")
class SessionExternalController(
    private val chatSessionService: ChatSessionService,
) {
    @PostMapping
    fun createOrGetSession(
        @RequestBody request: SessionExternalRequest,
        serverHttpRequest: ServerHttpRequest,
    ): SessionExternalResponse {
        val serverHint =
            ChatSessionService.generateServerHint(
                ipAddress = extractIpAddress(serverHttpRequest),
                userAgent = serverHttpRequest.headers.getFirst("User-Agent"),
            )

        val result =
            chatSessionService.getOrCreateSession(
                clientId = request.clientId,
                deviceHint = request.deviceHint,
                serverHint = serverHint,
            )

        return SessionExternalResponse.from(
            session = result.session,
            isNew = result.isNew,
        )
    }

    @GetMapping("/messages")
    fun getMessages(
        @RequestParam clientId: String,
    ): List<ChatMessageExternalResponse> {
        val messages = chatSessionService.getConversationHistory(clientId)
        return messages.map { ChatMessageExternalResponse.from(it) }
    }

    private fun extractIpAddress(request: ServerHttpRequest): String? =
        request.headers
            .getFirst("X-Forwarded-For")
            ?.split(",")
            ?.firstOrNull()
            ?.trim()
            ?: request.headers.getFirst("X-Real-IP")
            ?: request.remoteAddress?.address?.hostAddress
}
