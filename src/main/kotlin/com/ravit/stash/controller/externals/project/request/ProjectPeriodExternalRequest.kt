package com.ravit.stash.controller.externals.project.request

import java.time.LocalDate

data class ProjectPeriodExternalRequest(
    val startedAt: LocalDate,
    val endedAt: LocalDate?,
)
