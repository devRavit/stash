package com.ravit.stash.domain.session.repository

import com.ravit.stash.domain.session.document.ChatSession
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ChatSessionRepository : MongoRepository<ChatSession, ObjectId> {
    fun findByClientId(clientId: String): ChatSession?
}
