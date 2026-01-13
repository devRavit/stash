package com.ravit.stash.controller

import com.ravit.stash.domain.calendar.dto.CalendarEventRequest
import com.ravit.stash.domain.calendar.dto.CalendarEventResponse
import com.ravit.stash.domain.calendar.service.CalendarService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = ["http://localhost:3000", "https://ravit.run"])
class CalendarController(
    private val calendarService: CalendarService,
) {
    @PostMapping("/events")
    suspend fun createEvent(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody request: CalendarEventRequest,
    ): CalendarEventResponse {
        val accessToken = authorization.removePrefix("Bearer ").trim()
        return calendarService.createEvent(accessToken, request)
    }
}
