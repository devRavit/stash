package com.ravit.stash.health

data class MongoReplicaSetDetails(
    val nodes: List<MongoNodeHealth>,
)
