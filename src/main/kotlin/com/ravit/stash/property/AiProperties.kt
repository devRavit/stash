package com.ravit.stash.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ai.gemini")
data class AiProperties(
    val key: String,
    val model: String,
    val maxTokens: Int,
    val temperature: Double,
)
