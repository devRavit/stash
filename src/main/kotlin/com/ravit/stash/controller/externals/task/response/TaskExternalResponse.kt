package com.ravit.stash.controller.externals.task.response

import com.ravit.stash.controller.externals.shared.response.PeriodExternalResponse
import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.shared.annotation.WithKstZoneTime
import com.ravit.stash.shared.code.TaskType
import java.time.LocalDateTime

data class TaskExternalResponse(
    val id: String,
    val projectId: String,
    val type: TaskType,
    val title: String,
    val description: String?,
    val period: PeriodExternalResponse?,
    val workingDays: Int?,
    val details: TaskDetailsExternalResponse?,
    val keywords: List<String>,
    @WithKstZoneTime
    val createdAt: LocalDateTime,
    @WithKstZoneTime
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(task: Task): TaskExternalResponse =
            TaskExternalResponse(
                id = requireNotNull(task.id) { "Task id must not be null after save" }.toHexString(),
                projectId = task.projectId,
                type = task.type,
                title = task.title,
                description = task.description,
                period = task.period?.let { PeriodExternalResponse.from(it) },
                workingDays = task.workingDays,
                details = task.details?.let { TaskDetailsExternalResponse.from(it) },
                keywords = task.keywords,
                createdAt = requireNotNull(task.createdAt) { "Task createdAt must not be null" },
                updatedAt = requireNotNull(task.updatedAt) { "Task updatedAt must not be null" },
            )
    }
}
