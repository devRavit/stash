package com.ravit.stash.domain.session.service

import com.ravit.stash.domain.session.document.ChatMessage
import com.ravit.stash.domain.session.document.ChatRoleType
import com.ravit.stash.domain.session.document.ChatSession
import com.ravit.stash.domain.session.repository.ChatSessionRepository
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class ChatSessionService(
    private val chatSessionRepository: ChatSessionRepository,
) {
    fun getOrCreateSession(
        clientId: String,
        deviceHint: String?,
        serverHint: String?,
    ): SessionResult {
        val existingSession = chatSessionRepository.findByClientId(clientId)
        if (existingSession != null) {
            return SessionResult(
                session = existingSession,
                isNew = false,
            )
        }

        val newSession =
            ChatSession(
                clientId = clientId,
                deviceHint = deviceHint,
                serverHint = serverHint,
            )
        val savedSession = chatSessionRepository.save(newSession)
        return SessionResult(
            session = savedSession,
            isNew = true,
        )
    }

    fun addMessage(
        clientId: String,
        role: ChatRoleType,
        content: String,
    ): ChatSession {
        val session =
            chatSessionRepository.findByClientId(clientId)
                ?: throw IllegalArgumentException("Session not found for clientId: $clientId")

        session.messages.add(ChatMessage(role = role, content = content))
        return chatSessionRepository.save(session)
    }

    fun getSession(clientId: String): ChatSession? = chatSessionRepository.findByClientId(clientId)

    fun getConversationHistory(clientId: String): List<ChatMessage> =
        chatSessionRepository.findByClientId(clientId)?.messages ?: emptyList()

    data class SessionResult(
        val session: ChatSession,
        val isNew: Boolean,
    )

    companion object {
        fun generateServerHint(
            ipAddress: String?,
            userAgent: String?,
        ): String? {
            if (ipAddress == null && userAgent == null) return null
            val input = "${ipAddress.orEmpty()}|${userAgent.orEmpty()}"
            return hashSha256(input)
        }

        private fun hashSha256(input: String): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(input.toByteArray())
            return hashBytes.joinToString("") { "%02x".format(it) }
        }
    }
}
