package com.ravit.stash.infrastructure.google.calendar

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.model.Event
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import com.ravit.stash.domain.calendar.exception.CalendarException
import com.ravit.stash.domain.calendar.model.CalendarEvent
import com.ravit.stash.domain.calendar.model.CalendarEventCommand
import com.ravit.stash.infrastructure.client.CalendarClient
import com.ravit.stash.property.GoogleCalendarProperties
import com.ravit.stash.utility.DateTimeUtils.toLocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date

@Component
class GoogleCalendarClient(
    private val properties: GoogleCalendarProperties,
) : CalendarClient {
    private val logger = LoggerFactory.getLogger(GoogleCalendarClient::class.java)
    private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()

    companion object {
        fun from(event: Event): CalendarEvent =
            CalendarEvent(
                id = event.id,
                summary = event.summary,
                htmlLink = event.htmlLink,
                startDateTime = event.start.toLocalDateTime(),
                endDateTime = event.end.toLocalDateTime(),
            )
    }

    override suspend fun createEvent(
        accessToken: String,
        command: CalendarEventCommand,
    ): CalendarEvent =
        withContext(Dispatchers.IO) {
            try {
                val calendarService = buildCalendarService(accessToken)
                val targetCalendarId = command.calendarId ?: properties.calendarId

                val event = buildEvent(command)

                logger.debug("Creating calendar event: summary=${command.summary}, calendarId=$targetCalendarId")

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

    private fun buildEvent(command: CalendarEventCommand): Event =
        EventBuilder {
            summary = command.summary
            description = command.description
            location = command.location
            startDateTime = command.startDateTime
            endDateTime = command.endDateTime
            timezone = command.timezone
            attendees = command.attendees
        }.build()
}
