package com.ravit.stash.service

import com.ravit.stash.domain.ChatAgent
import org.springframework.stereotype.Service

@Service
class AiService(
    private val chatAgent: ChatAgent,
) {
    suspend fun chat(message: String): String = chatAgent.chat(message)
}
