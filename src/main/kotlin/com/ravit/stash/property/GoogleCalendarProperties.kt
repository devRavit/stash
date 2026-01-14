package com.ravit.stash.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "google.calendar")
data class GoogleCalendarProperties(
    val applicationName: String,
    val calendarId: String,
)
