package com.ravit.stash.actuator

sealed interface DependencyDetails

data class MongoDBDetails(
    val replicaSet: MongoReplicaSetDetails?,
) : DependencyDetails
