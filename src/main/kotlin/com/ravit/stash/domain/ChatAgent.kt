package com.ravit.stash.domain

interface ChatAgent {
    suspend fun chat(message: String): String
}
