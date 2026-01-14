package com.ravit.stash.controller.externals.task.response

import java.time.LocalDate

data class TaskPeriodExternalResponse(
    val startedAt: LocalDate?,
    val completedAt: LocalDate?,
)
