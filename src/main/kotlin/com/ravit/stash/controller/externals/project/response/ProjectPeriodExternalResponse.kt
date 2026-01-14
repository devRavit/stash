package com.ravit.stash.controller.externals.project.response

import java.time.LocalDate

data class ProjectPeriodExternalResponse(
    val startedAt: LocalDate,
    val endedAt: LocalDate?,
)
