package com.ravit.stash.ai.model.command

import com.ravit.stash.domain.session.document.ChatMessage

data class AiChatCommand(
    val clientId: String,
    val message: String,
    val history: List<ChatMessage> = emptyList(),
)
