package com.ravit.stash.health

sealed interface DependencyDetails

data class MongoDBDetails(
    val replicaSet: MongoReplicaSetDetails?,
) : DependencyDetails
