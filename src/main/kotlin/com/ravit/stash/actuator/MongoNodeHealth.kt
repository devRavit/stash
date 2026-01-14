package com.ravit.stash.actuator

import com.ravit.stash.shared.code.MongoNodeStateType

data class MongoNodeHealth(
    val state: MongoNodeStateType,
    val healthy: Boolean,
)
