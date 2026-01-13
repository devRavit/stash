package com.ravit.stash.domain.calendar.exception

sealed class CalendarException(
    message: String,
) : RuntimeException(message) {
    class InvalidAccessTokenException(
        message: String = "Invalid or expired access token",
    ) : CalendarException(message)

    class CalendarApiException(
        message: String,
    ) : CalendarException(message)

    class EventCreationFailedException(
        message: String,
    ) : CalendarException(message)
}
