package com.ravit.stash.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "web")
data class WebProperties(
    val cors: CorsSettings,
) {
    data class CorsSettings(
        val allowedOrigins: List<String>,
    )
}
