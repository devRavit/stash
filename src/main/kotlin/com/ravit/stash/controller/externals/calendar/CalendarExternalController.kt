package com.ravit.stash.controller.externals.calendar

import com.ravit.stash.controller.externals.calendar.request.CalendarEventExternalRequest
import com.ravit.stash.controller.externals.calendar.response.CalendarEventExternalResponse
import com.ravit.stash.domain.calendar.dto.CalendarEventRequest
import com.ravit.stash.domain.calendar.service.CalendarService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/externals/calendar")
@CrossOrigin(origins = ["http://localhost:3000", "https://ravit.run"])
class CalendarExternalController(
    private val calendarService: CalendarService,
) {
    @PostMapping("/events")
    suspend fun createEvent(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: CalendarEventExternalRequest,
    ): CalendarEventExternalResponse {
        val accessToken = authorization.removePrefix("Bearer ").trim()
        val command =
            CalendarEventRequest(
                summary = request.summary,
                description = request.description,
                location = request.location,
                startDateTime = request.startDateTime,
                endDateTime = request.endDateTime,
                timezone = request.timezone,
                attendees = request.attendees,
                calendarId = request.calendarId,
            )
        val result = calendarService.createEvent(accessToken, command)
        return CalendarEventExternalResponse(
            id = result.id,
            summary = result.summary,
            htmlLink = result.htmlLink,
            startDateTime = result.startDateTime,
            endDateTime = result.endDateTime,
        )
    }
}
