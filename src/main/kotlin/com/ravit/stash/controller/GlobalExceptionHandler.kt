package com.ravit.stash.controller

import com.ravit.stash.domain.calendar.exception.CalendarException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(CalendarException.InvalidAccessTokenException::class)
    fun handleInvalidAccessTokenException(e: CalendarException.InvalidAccessTokenException): ResponseEntity<ErrorResponse> {
        logger.warn("Invalid access token: ${e.message}")
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.message ?: "Invalid or expired access token"))
    }

    @ExceptionHandler(CalendarException.CalendarApiException::class)
    fun handleCalendarApiException(e: CalendarException.CalendarApiException): ResponseEntity<ErrorResponse> {
        logger.error("Calendar API error: ${e.message}", e)
        return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .body(ErrorResponse(HttpStatus.BAD_GATEWAY.value(), e.message ?: "Calendar API error"))
    }

    @ExceptionHandler(CalendarException.EventCreationFailedException::class)
    fun handleEventCreationFailedException(e: CalendarException.EventCreationFailedException): ResponseEntity<ErrorResponse> {
        logger.error("Event creation failed: ${e.message}", e)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message ?: "Event creation failed"))
    }

    data class ErrorResponse(
        val status: Int,
        val message: String,
    )
}
