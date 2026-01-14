package com.ravit.stash.controller.externals.calendar.request

import java.time.LocalDateTime

data class CalendarEventExternalRequest(
    val summary: String,
    val description: String? = null,
    val location: String? = null,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val timezone: String = "Asia/Seoul",
    val attendees: List<String> = emptyList(),
    val calendarId: String? = null,
)
