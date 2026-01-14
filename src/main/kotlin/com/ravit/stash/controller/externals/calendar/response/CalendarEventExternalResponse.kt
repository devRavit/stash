package com.ravit.stash.controller.externals.calendar.response

import java.time.LocalDateTime

data class CalendarEventExternalResponse(
    val id: String,
    val summary: String,
    val htmlLink: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
