package com.ravit.stash.ai.agent

interface ChatAgent {
    suspend fun chat(message: String): String
}
