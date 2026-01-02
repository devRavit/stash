package com.ravit.stash.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GoogleCalendarProperties::class)
class GoogleCalendarConfiguration
