package com.ravit.stash.ai.service

import com.ravit.stash.ai.agent.ChatAgent
import com.ravit.stash.ai.model.command.AiChatCommand
import org.springframework.stereotype.Service

@Service
class AiService(
    private val chatAgent: ChatAgent,
) {
    suspend fun chat(command: AiChatCommand): String = chatAgent.chat(command.message)
}
