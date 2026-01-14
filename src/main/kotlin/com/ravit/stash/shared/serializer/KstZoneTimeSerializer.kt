package com.ravit.stash.shared.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.ContextualSerializer
import com.ravit.stash.shared.annotation.WithKstZoneTime
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class KstZoneTimeSerializer(
    private val formatter: DateTimeFormatter = DEFAULT_FORMATTER,
) : JsonSerializer<LocalDateTime>(),
    ContextualSerializer {
    override fun serialize(
        value: LocalDateTime,
        generator: JsonGenerator,
        serializers: SerializerProvider,
    ) {
        val kstTime = value.atZone(KST_ZONE)
        generator.writeString(kstTime.format(formatter))
    }

    override fun createContextual(
        provider: SerializerProvider,
        property: BeanProperty?,
    ): JsonSerializer<*> {
        val annotation =
            property?.getAnnotation(WithKstZoneTime::class.java)
                ?: return this
        if (annotation.format == WithKstZoneTime.DEFAULT_FORMAT) {
            return this
        }
        return KstZoneTimeSerializer(DateTimeFormatter.ofPattern(annotation.format))
    }

    companion object {
        private val KST_ZONE = ZoneId.of("Asia/Seoul")
        private val DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(WithKstZoneTime.DEFAULT_FORMAT)
    }
}
