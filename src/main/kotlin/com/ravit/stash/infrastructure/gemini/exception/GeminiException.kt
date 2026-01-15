package com.ravit.stash.infrastructure.gemini.exception

sealed class GeminiException(
    message: String,
) : RuntimeException(message) {
    class RateLimitExceededException(
        message: String = "API rate limit exceeded. Please try again later.",
    ) : GeminiException(message)

    class ApiException(
        message: String,
    ) : GeminiException(message)
}
