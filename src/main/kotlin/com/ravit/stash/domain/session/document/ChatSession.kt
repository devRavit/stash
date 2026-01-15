package com.ravit.stash.domain.session.document

import com.ravit.stash.shared.document.BaseDocument
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "chat_sessions")
@CompoundIndex(name = "device_server_hint_idx", def = "{'deviceHint': 1, 'serverHint': 1}")
class ChatSession(
    @Indexed(unique = true)
    val clientId: String,
    val deviceHint: String?,
    val serverHint: String?,
    val messages: MutableList<ChatMessage> = mutableListOf(),
) : BaseDocument()
