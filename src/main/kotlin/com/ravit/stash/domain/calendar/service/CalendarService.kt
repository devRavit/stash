package com.ravit.stash.domain.calendar.service

import com.ravit.stash.domain.calendar.client.CalendarClient
import com.ravit.stash.domain.calendar.dto.CalendarEventRequest
import com.ravit.stash.domain.calendar.dto.CalendarEventResponse
import org.springframework.stereotype.Service

@Service
class CalendarService(
    private val calendarClient: CalendarClient,
) {
    suspend fun createEvent(
        accessToken: String,
        request: CalendarEventRequest,
    ): CalendarEventResponse = calendarClient.createEvent(accessToken, request)
}
