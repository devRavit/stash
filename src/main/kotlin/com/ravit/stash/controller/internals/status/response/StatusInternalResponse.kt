package com.ravit.stash.controller.internals.status.response

import com.ravit.stash.actuator.DependencyHealth
import com.ravit.stash.shared.code.HealthStatusType

data class StatusInternalResponse(
    val service: String,
    val version: String,
    val status: HealthStatusType,
    val dependencies: List<DependencyHealth>,
)
