package com.ravit.stash.infrastructure.google.calendar

import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.ravit.stash.utility.DateTimeUtils.toEventDateTime
import java.time.LocalDateTime

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
