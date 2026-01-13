package com.ravit.stash.domain.calendar.dto

import java.time.LocalDateTime

data class CalendarEventResponse(
    val id: String,
    val summary: String,
    val htmlLink: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
