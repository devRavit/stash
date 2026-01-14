package com.ravit.stash.configuration

import com.mongodb.ReadPreference
import org.springframework.boot.mongodb.autoconfigure.MongoClientSettingsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import java.util.concurrent.TimeUnit

@Configuration
class MongoConfiguration {
    @Bean
    fun mappingMongoConverter(
        mongoDatabaseFactory: MongoDatabaseFactory,
        mongoMappingContext: MongoMappingContext,
        mongoCustomConversions: MongoCustomConversions,
    ): MappingMongoConverter {
        val converter = MappingMongoConverter(mongoDatabaseFactory, mongoMappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        converter.setCustomConversions(mongoCustomConversions)
        return converter
    }

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

    @Bean
    fun secondaryMongoTemplate(
        mongoDatabaseFactory: MongoDatabaseFactory,
        converter: MappingMongoConverter,
    ): MongoTemplate {
        val template = MongoTemplate(mongoDatabaseFactory, converter)
        template.setReadPreference(ReadPreference.secondaryPreferred())
        return template
    }

    @Bean
    fun mongoTemplate(
        mongoDatabaseFactory: MongoDatabaseFactory,
        converter: MappingMongoConverter,
    ): MongoTemplate = MongoTemplate(mongoDatabaseFactory, converter)
}
