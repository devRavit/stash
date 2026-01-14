package com.ravit.stash.controller.externals.task.request

import com.ravit.stash.domain.task.document.TaskDetails

data class TaskDetailsExternalRequest(
    val background: String? = null,
    val solution: String? = null,
    val impact: String? = null,
) {
    fun toTaskDetails(): TaskDetails =
        TaskDetails(
            background = background,
            solution = solution,
            impact = impact,
        )
}
