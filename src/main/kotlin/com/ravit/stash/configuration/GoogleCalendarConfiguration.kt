package com.ravit.stash.configuration

import com.ravit.stash.property.GoogleCalendarProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GoogleCalendarProperties::class)
class GoogleCalendarConfiguration
