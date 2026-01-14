package com.ravit.stash.domain.calendar.service

import com.ravit.stash.domain.calendar.model.CalendarEvent
import com.ravit.stash.domain.calendar.model.CalendarEventCommand
import com.ravit.stash.infrastructure.client.CalendarClient
import org.springframework.stereotype.Service

@Service
class CalendarService(
    private val calendarClient: CalendarClient,
) {
    suspend fun createEvent(
        accessToken: String,
        command: CalendarEventCommand,
    ): CalendarEvent = calendarClient.createEvent(accessToken, command)
}
