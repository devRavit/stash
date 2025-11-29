package com.ravit.stash.configuration

import org.springframework.boot.mongodb.autoconfigure.MongoClientSettingsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class MongoConfiguration {
    @Bean
    fun mongoClientSettingsCustomizer(): MongoClientSettingsBuilderCustomizer =
        MongoClientSettingsBuilderCustomizer { builder ->
            builder
                .applyToConnectionPoolSettings { pool ->
                    pool
                        .maxSize(10)
                        .minSize(2)
                        .maxWaitTime(5, TimeUnit.SECONDS)
                        .maxConnectionIdleTime(30, TimeUnit.SECONDS)
                }.applyToSocketSettings { socket ->
                    socket
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                }.applyToClusterSettings { cluster ->
                    cluster.serverSelectionTimeout(5, TimeUnit.SECONDS)
                }
        }
}
