package com.ravit.stash.shared.annotation

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.ravit.stash.shared.serializer.KstZoneTimeSerializer

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = KstZoneTimeSerializer::class)
annotation class WithKstZoneTime(
    val format: String = DEFAULT_FORMAT,
) {
    companion object {
        const val DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }
}
