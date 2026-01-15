package com.ravit.stash.ai.service

import com.ravit.stash.ai.model.command.AiChatCommand
import com.ravit.stash.domain.session.document.ChatMessage
import com.ravit.stash.domain.session.document.ChatRoleType
import com.ravit.stash.domain.session.service.ChatSessionService
import com.ravit.stash.infrastructure.gemini.GeminiClient
import com.ravit.stash.infrastructure.gemini.request.GeminiRequestContent
import com.ravit.stash.infrastructure.gemini.request.GeminiRequestPart
import com.ravit.stash.property.AiProperties
import org.springframework.stereotype.Service

@Service
class AiService(
    private val geminiClient: GeminiClient,
    private val chatSessionService: ChatSessionService,
    private val aiProperties: AiProperties,
) {
    suspend fun chat(command: AiChatCommand): String {
        val history = chatSessionService.getConversationHistory(command.clientId)

        val contents = buildContents(history, command.message)
        val response = geminiClient.generateContentWithHistory(aiProperties.model, contents)

        chatSessionService.addMessage(command.clientId, ChatRoleType.USER, command.message)
        chatSessionService.addMessage(command.clientId, ChatRoleType.MODEL, response)

        return response
    }

    private fun buildContents(
        history: List<ChatMessage>,
        newMessage: String,
    ): List<GeminiRequestContent> {
        val contents = mutableListOf<GeminiRequestContent>()

        history.forEach { message ->
            contents.add(
                GeminiRequestContent(
                    role = message.role.toGeminiRole(),
                    parts = listOf(GeminiRequestPart(text = message.content)),
                ),
            )
        }

        contents.add(
            GeminiRequestContent(
                role = "user",
                parts = listOf(GeminiRequestPart(text = newMessage)),
            ),
        )

        return contents
    }

    private fun ChatRoleType.toGeminiRole(): String =
        when (this) {
            ChatRoleType.USER -> "user"
            ChatRoleType.MODEL -> "model"
        }
}
