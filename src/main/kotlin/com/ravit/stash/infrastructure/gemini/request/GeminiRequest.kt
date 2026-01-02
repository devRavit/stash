package com.ravit.stash.infrastructure.gemini.request

data class GeminiRequest(
    val contents: List<GeminiRequestContent>,
)

data class GeminiRequestContent(
    val parts: List<GeminiRequestPart>,
)

data class GeminiRequestPart(
    val text: String,
)
