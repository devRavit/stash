package com.ravit.stash.health

import com.ravit.stash.shared.code.DependencyType

interface DependencyHealthIndicator {
    val type: DependencyType

    fun check(): DependencyHealth
}
