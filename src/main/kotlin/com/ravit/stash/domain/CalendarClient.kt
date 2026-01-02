package com.ravit.stash.domain

interface CalendarClient {
    suspend fun createEvent(
        accessToken: String,
        request: CalendarEventRequest,
    ): CalendarEventResponse
}
