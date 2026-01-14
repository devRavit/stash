package com.ravit.stash.controller.externals.task.response

import com.ravit.stash.domain.task.document.TaskDetails

data class TaskDetailsExternalResponse(
    val background: String?,
    val solution: String?,
    val impact: String?,
) {
    companion object {
        fun from(details: TaskDetails): TaskDetailsExternalResponse =
            TaskDetailsExternalResponse(
                background = details.background,
                solution = details.solution,
                impact = details.impact,
            )
    }
}
