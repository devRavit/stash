package com.ravit.stash.controller

import org.springframework.boot.info.BuildProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class HealthResponse(
    val status: String,
    val service: String,
    val version: String,
)

@RestController
@RequestMapping("/health")
class HealthController(
    private val buildProperties: BuildProperties,
) {
    @GetMapping
    fun health(): HealthResponse =
        HealthResponse(
            status = "UP",
            service = "stash",
            version = buildProperties.version ?: "unknown",
        )
}
