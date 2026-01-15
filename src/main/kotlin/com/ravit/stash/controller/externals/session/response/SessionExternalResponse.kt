package com.ravit.stash.controller.externals.session.response

import com.ravit.stash.domain.session.document.ChatSession

data class SessionExternalResponse(
    val sessionId: String,
    val isNew: Boolean,
    val messageCount: Int,
) {
    companion object {
        fun from(
            session: ChatSession,
            isNew: Boolean,
        ): SessionExternalResponse =
            SessionExternalResponse(
                sessionId = session.id?.toHexString() ?: "",
                isNew = isNew,
                messageCount = session.messages.size,
            )
    }
}
