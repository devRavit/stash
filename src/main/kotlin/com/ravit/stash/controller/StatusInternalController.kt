package com.ravit.stash.controller

import org.springframework.boot.info.BuildProperties
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class StatusResponse(
    val service: String,
    val version: String,
    val database: DatabaseStatus,
)

data class DatabaseStatus(
    val status: String,
    val type: String,
)

@RestController
@RequestMapping("/internal/status")
class StatusInternalController(
    private val buildProperties: BuildProperties,
    private val mongoTemplate: MongoTemplate,
) {
    @GetMapping
    fun status(): StatusResponse {
        val dbStatus = checkDatabase()

        return StatusResponse(
            service = "stash",
            version = buildProperties.version ?: "unknown",
            database = dbStatus,
        )
    }

    private fun checkDatabase(): DatabaseStatus =
        try {
            mongoTemplate.db.runCommand(org.bson.Document("ping", 1))
            DatabaseStatus(status = "UP", type = "MongoDB")
        } catch (e: Exception) {
            DatabaseStatus(status = "DOWN", type = "MongoDB")
        }
}
