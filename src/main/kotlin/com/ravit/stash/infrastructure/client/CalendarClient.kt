package com.ravit.stash.infrastructure.client

import com.ravit.stash.domain.calendar.model.CalendarEvent
import com.ravit.stash.domain.calendar.model.CalendarEventCommand

interface CalendarClient {
    suspend fun createEvent(
        accessToken: String,
        command: CalendarEventCommand,
    ): CalendarEvent
}
