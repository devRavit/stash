package com.ravit.stash.controller.externals.ai.response

data class AiChatExternalResponse(
    val response: String,
    val isError: Boolean = false,
)
