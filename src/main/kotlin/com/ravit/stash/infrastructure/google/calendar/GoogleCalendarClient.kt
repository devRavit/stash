package com.ravit.stash.infrastructure.google.calendar

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import com.ravit.stash.configuration.GoogleCalendarProperties
import com.ravit.stash.domain.CalendarClient
import com.ravit.stash.domain.CalendarEventRequest
import com.ravit.stash.domain.CalendarEventResponse
import com.ravit.stash.domain.exception.CalendarException
import com.ravit.stash.utility.DateTimeUtils.toEventDateTime
import com.ravit.stash.utility.DateTimeUtils.toLocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date

@Component
class GoogleCalendarClient(
    private val properties: GoogleCalendarProperties,
) : CalendarClient {
    private val logger = LoggerFactory.getLogger(GoogleCalendarClient::class.java)
    private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()

    companion object {
        fun from(event: Event): CalendarEventResponse =
            CalendarEventResponse(
                id = event.id,
                summary = event.summary,
                htmlLink = event.htmlLink,
                startDateTime = event.start.toLocalDateTime(),
                endDateTime = event.end.toLocalDateTime(),
            )
    }

    override suspend fun createEvent(
        accessToken: String,
        request: CalendarEventRequest,
    ): CalendarEventResponse =
        withContext(Dispatchers.IO) {
            try {
                val calendarService = buildCalendarService(accessToken)
                val targetCalendarId = request.calendarId ?: properties.calendarId

                val event = buildEvent(request)

                logger.debug("Creating calendar event: summary=${request.summary}, calendarId=$targetCalendarId")

                val createdEvent =
                    calendarService
                        .events()
                        .insert(targetCalendarId, event)
                        .execute()

                logger.debug("Calendar event created: id=${createdEvent.id}")

                Companion.from(createdEvent)
            } catch (e: Exception) {
                logger.error("Failed to create calendar event: ${e.message}", e)
                when {
                    e.message?.contains("401") == true || e.message?.contains("Unauthorized") == true ->
                        throw CalendarException.InvalidAccessTokenException(e.message ?: "Unauthorized")

                    e.message?.contains("API") == true ->
                        throw CalendarException.CalendarApiException(e.message ?: "Calendar API error")

                    else -> throw CalendarException.EventCreationFailedException(e.message ?: "Event creation failed")
                }
            }
        }

    private fun buildCalendarService(accessToken: String): Calendar {
        val expirationTime = Date(System.currentTimeMillis() + 3600 * 1000)
        val credentials = GoogleCredentials.create(AccessToken(accessToken, expirationTime))

        return Calendar
            .Builder(httpTransport, jsonFactory, HttpCredentialsAdapter(credentials))
            .setApplicationName(properties.applicationName)
            .build()
    }

    private fun buildEvent(request: CalendarEventRequest): Event =
        EventBuilder {
            summary = request.summary
            description = request.description
            location = request.location
            startDateTime = request.startDateTime
            endDateTime = request.endDateTime
            timezone = request.timezone
            attendees = request.attendees
        }.build()
}

class EventBuilder private constructor() {
    var summary: String? = null
    var description: String? = null
    var location: String? = null
    var startDateTime: LocalDateTime? = null
    var endDateTime: LocalDateTime? = null
    var timezone: String = "Asia/Seoul"
    var attendees: List<String> = emptyList()

    companion object {
        operator fun invoke(block: EventBuilder.() -> Unit): EventBuilder = EventBuilder().apply(block)
    }

    fun build(): Event =
        Event()
            .setSummary(summary)
            .setDescription(description)
            .setLocation(location)
            .setStart(startDateTime?.toEventDateTime(timezone))
            .setEnd(endDateTime?.toEventDateTime(timezone))
            .setAttendees(
                attendees.map { email ->
                    EventAttendee().setEmail(email)
                },
            )
}
