package com.ravit.stash.health

import com.ravit.stash.shared.code.MongoNodeStateType

data class MongoNodeHealth(
    val state: MongoNodeStateType,
    val healthy: Boolean,
)
