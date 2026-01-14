package com.ravit.stash.controller.externals.task.request

import com.ravit.stash.controller.externals.shared.request.PeriodExternalRequest
import com.ravit.stash.shared.code.TaskType

data class TaskExternalRequest(
    val id: String? = null,
    val projectId: String,
    val type: TaskType,
    val title: String,
    val description: String? = null,
    val period: PeriodExternalRequest? = null,
    val workingDays: Int? = null,
    val details: TaskDetailsExternalRequest? = null,
    val keywords: List<String>? = null,
)
