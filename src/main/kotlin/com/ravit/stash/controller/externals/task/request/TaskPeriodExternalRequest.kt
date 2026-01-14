package com.ravit.stash.controller.externals.task.request

import java.time.LocalDate

data class TaskPeriodExternalRequest(
    val startedAt: LocalDate?,
    val completedAt: LocalDate?,
)
