package com.ravit.stash.controller.externals.task.response

import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.shared.code.TaskType
import java.time.LocalDateTime

data class TaskExternalResponse(
    val id: String,
    val projectId: String,
    val type: TaskType,
    val title: String,
    val description: String?,
    val period: TaskPeriodExternalResponse?,
    val workingDays: Int?,
    val details: TaskDetailsExternalResponse?,
    val keywords: List<String>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(task: Task): TaskExternalResponse =
            TaskExternalResponse(
                id = task.id,
                projectId = task.projectId,
                type = task.type,
                title = task.title,
                description = task.description,
                period =
                    task.period?.let {
                        TaskPeriodExternalResponse(
                            startedAt = it.startedAt,
                            completedAt = it.completedAt,
                        )
                    },
                workingDays = task.workingDays,
                details =
                    task.details?.let {
                        TaskDetailsExternalResponse(
                            background = it.background,
                            solution = it.solution,
                            impact = it.impact,
                        )
                    },
                keywords = task.keywords,
                createdAt = task.createdAt,
                updatedAt = task.updatedAt,
            )
    }
}
