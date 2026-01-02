package com.ravit.stash.infrastructure.gemini.response

data class GeminiResponse(
    val candidates: List<GeminiCandidate> = emptyList(),
)

data class GeminiCandidate(
    val content: GeminiContent? = null,
)

data class GeminiContent(
    val parts: List<GeminiPart> = emptyList(),
)

data class GeminiPart(
    val text: String = "",
)
