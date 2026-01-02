package com.ravit.stash.domain

import java.time.LocalDateTime

data class CalendarEventResponse(
    val id: String,
    val summary: String,
    val htmlLink: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
