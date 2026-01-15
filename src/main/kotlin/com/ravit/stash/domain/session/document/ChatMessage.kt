package com.ravit.stash.domain.session.document

import java.time.LocalDateTime

data class ChatMessage(
    val role: ChatRoleType,
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
