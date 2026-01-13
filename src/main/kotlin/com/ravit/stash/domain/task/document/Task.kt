package com.ravit.stash.domain.task.document

import com.ravit.stash.shared.code.TaskType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "tasks")
data class Task(
    @Id
    val id: String,
    val projectId: String,
    val type: TaskType,
    val title: String,
    val description: String? = null,
    val period: TaskPeriod? = null,
    val workingDays: Int? = null,
    val details: TaskDetails? = null,
    val keywords: List<String> = emptyList(),
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
