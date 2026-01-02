package com.ravit.stash.service

import com.ravit.stash.domain.CalendarClient
import com.ravit.stash.domain.CalendarEventRequest
import com.ravit.stash.domain.CalendarEventResponse
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
