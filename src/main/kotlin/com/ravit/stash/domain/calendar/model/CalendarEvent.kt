package com.ravit.stash.domain.calendar.model

import java.time.LocalDateTime

data class CalendarEvent(
    val id: String,
    val summary: String,
    val htmlLink: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
