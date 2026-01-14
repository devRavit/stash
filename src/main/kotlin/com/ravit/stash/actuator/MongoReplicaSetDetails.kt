package com.ravit.stash.actuator

data class MongoReplicaSetDetails(
    val nodes: List<MongoNodeHealth>,
)
