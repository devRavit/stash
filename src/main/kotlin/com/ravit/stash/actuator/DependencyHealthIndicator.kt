package com.ravit.stash.actuator

import com.ravit.stash.shared.code.DependencyType

interface DependencyHealthIndicator {
    val type: DependencyType

    fun check(): DependencyHealth
}
