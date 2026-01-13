package com.ravit.stash.health

import com.ravit.stash.shared.code.DependencyType
import com.ravit.stash.shared.code.HealthStatusType

data class DependencyHealth(
    val type: DependencyType,
    val status: HealthStatusType,
    val details: DependencyDetails? = null,
)
