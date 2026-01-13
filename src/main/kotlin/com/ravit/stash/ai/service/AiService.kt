package com.ravit.stash.ai.service

import com.ravit.stash.ai.agent.ChatAgent
import org.springframework.stereotype.Service

@Service
class AiService(
    private val chatAgent: ChatAgent,
) {
    suspend fun chat(message: String): String = chatAgent.chat(message)
}
