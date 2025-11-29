package com.ravit.stash.health

enum class DependencyType(
    val critical: Boolean,
) {
    MONGODB(critical = true),
    JIRA(critical = false),
    SLACK(critical = false),
}

enum class HealthStatusType {
    UP,
    DEGRADED,
    DOWN,
}

enum class MongoNodeStateType {
    PRIMARY,
    SECONDARY,
    ARBITER,
    UNKNOWN,
}

data class MongoNodeHealth(
    val state: MongoNodeStateType,
    val healthy: Boolean,
)

data class MongoReplicaSetDetails(
    val nodes: List<MongoNodeHealth>,
)

sealed interface DependencyDetails

data class MongoDBDetails(
    val replicaSet: MongoReplicaSetDetails?,
) : DependencyDetails

data class DependencyHealth(
    val type: DependencyType,
    val status: HealthStatusType,
    val details: DependencyDetails? = null,
)

interface DependencyHealthIndicator {
    val type: DependencyType

    fun check(): DependencyHealth
}
