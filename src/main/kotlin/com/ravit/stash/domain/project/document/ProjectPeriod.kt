package com.ravit.stash.domain.project.document

import java.time.LocalDate

data class ProjectPeriod(
    val startedAt: LocalDate,
    val endedAt: LocalDate?,
)
