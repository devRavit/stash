package com.ravit.stash.health

import com.mongodb.client.MongoClient
import com.mongodb.connection.ServerDescription
import com.mongodb.connection.ServerType
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class MongoDBHealthIndicator(
    private val mongoTemplate: MongoTemplate,
    private val mongoClient: MongoClient,
) : DependencyHealthIndicator {
    override val type = DependencyType.MONGODB

    override fun check(): DependencyHealth =
        try {
            mongoTemplate.db.runCommand(Document("ping", 1))
            val replicaSetDetails = getReplicaSetFromClusterDescription()
            val status = calculateOverallStatus(replicaSetDetails)
            DependencyHealth(
                type = type,
                status = status,
                details = MongoDBDetails(replicaSet = replicaSetDetails),
            )
        } catch (e: Exception) {
            DependencyHealth(type = type, status = HealthStatusType.DOWN)
        }

    private fun calculateOverallStatus(replicaSet: MongoReplicaSetDetails?): HealthStatusType {
        if (replicaSet == null) return HealthStatusType.UP

        val nodes = replicaSet.nodes
        val healthyCount = nodes.count { it.healthy }
        val hasPrimary = nodes.any { it.state == MongoNodeStateType.PRIMARY && it.healthy }

        return when {
            healthyCount == 0 -> HealthStatusType.DOWN
            !hasPrimary || healthyCount < nodes.size -> HealthStatusType.DEGRADED
            else -> HealthStatusType.UP
        }
    }

    private fun getReplicaSetFromClusterDescription(): MongoReplicaSetDetails? =
        try {
            val clusterDescription = mongoClient.clusterDescription
            val servers = clusterDescription.serverDescriptions

            if (servers.isEmpty()) {
                null
            } else {
                val nodes = servers.map { server -> server.toMongoNodeHealth() }
                MongoReplicaSetDetails(nodes = nodes)
            }
        } catch (e: Exception) {
            null
        }

    private fun ServerDescription.toMongoNodeHealth(): MongoNodeHealth =
        MongoNodeHealth(
            state = parseServerType(type),
            healthy = isOk,
        )

    private fun parseServerType(serverType: ServerType): MongoNodeStateType =
        when (serverType) {
            ServerType.REPLICA_SET_PRIMARY -> MongoNodeStateType.PRIMARY
            ServerType.REPLICA_SET_SECONDARY -> MongoNodeStateType.SECONDARY
            ServerType.REPLICA_SET_ARBITER -> MongoNodeStateType.ARBITER
            else -> MongoNodeStateType.UNKNOWN
        }
}
