package com.ravit.stash.domain.calendar.client

import com.ravit.stash.domain.calendar.dto.CalendarEventRequest
import com.ravit.stash.domain.calendar.dto.CalendarEventResponse

interface CalendarClient {
    suspend fun createEvent(
        accessToken: String,
        request: CalendarEventRequest,
    ): CalendarEventResponse
}
