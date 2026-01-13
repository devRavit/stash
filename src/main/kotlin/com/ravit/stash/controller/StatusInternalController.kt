package com.ravit.stash.controller

import com.ravit.stash.health.DependencyHealth
import com.ravit.stash.health.DependencyHealthIndicator
import com.ravit.stash.shared.code.HealthStatusType
import org.springframework.boot.info.BuildProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class StatusResponse(
    val service: String,
    val version: String,
    val status: HealthStatusType,
    val dependencies: List<DependencyHealth>,
)

@RestController
@RequestMapping("/internal/status")
class StatusInternalController(
    private val buildProperties: BuildProperties?,
    private val healthIndicators: List<DependencyHealthIndicator>,
) {
    @GetMapping
    fun status(): StatusResponse {
        val results =
            healthIndicators.map { indicator ->
                indicator to indicator.check()
            }
        val dependencies = results.map { it.second }

        val overallStatus = calculateOverallStatus(results)

        return StatusResponse(
            service = "stash",
            version = buildProperties?.version ?: "unknown",
            status = overallStatus,
            dependencies = dependencies,
        )
    }

    private fun calculateOverallStatus(results: List<Pair<DependencyHealthIndicator, DependencyHealth>>): HealthStatusType {
        val hasCriticalDown =
            results.any { (indicator, health) ->
                indicator.type.critical && health.status == HealthStatusType.DOWN
            }
        if (hasCriticalDown) return HealthStatusType.DOWN

        val hasAnyIssue =
            results.any { (_, health) ->
                health.status != HealthStatusType.UP
            }
        if (hasAnyIssue) return HealthStatusType.DEGRADED

        return HealthStatusType.UP
    }
}
