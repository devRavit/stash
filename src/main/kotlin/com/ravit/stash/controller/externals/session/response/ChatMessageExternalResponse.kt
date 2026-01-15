package com.ravit.stash.controller.externals.session.response

import com.ravit.stash.domain.session.document.ChatMessage
import java.time.LocalDateTime

data class ChatMessageExternalResponse(
    val role: String,
    val content: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(message: ChatMessage): ChatMessageExternalResponse =
            ChatMessageExternalResponse(
                role = message.role.name.lowercase(),
                content = message.content,
                createdAt = message.createdAt,
            )
    }
}
