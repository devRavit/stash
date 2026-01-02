package com.ravit.stash.utility

import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.EventDateTime
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    fun EventDateTime.toLocalDateTime(): LocalDateTime {
        val zonedDateTime =
            Instant
                .ofEpochMilli(dateTime.value)
                .atZone(ZoneId.systemDefault())
        return zonedDateTime.toLocalDateTime()
    }

    fun LocalDateTime.toEventDateTime(timeZone: String): EventDateTime {
        val zonedDateTime = this.atZone(ZoneId.of(timeZone))
        val rfc3339String = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(zonedDateTime)

        return EventDateTime()
            .setDateTime(DateTime(rfc3339String))
            .setTimeZone(timeZone)
    }
}
