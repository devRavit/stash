package com.ravit.stash.domain.task.document

import java.time.LocalDate

data class TaskPeriod(
    val startedAt: LocalDate?,
    val completedAt: LocalDate?,
)
