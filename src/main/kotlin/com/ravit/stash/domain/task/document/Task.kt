package com.ravit.stash.domain.task.document

import com.ravit.stash.shared.code.TaskType
import com.ravit.stash.shared.document.BaseDocument
import com.ravit.stash.shared.document.Period
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tasks")
class Task(
    @Indexed
    val projectId: String,
    @Indexed
    val type: TaskType,
    val title: String,
    val description: String? = null,
    val period: Period? = null,
    val workingDays: Int? = null,
    val details: TaskDetails? = null,
    var keywords: List<String> = emptyList(),
) : BaseDocument() {
    fun updateKeywords(newKeywords: List<String>) =
        apply {
            keywords = newKeywords
        }
}

data class TaskDetails(
    val background: String? = null,
    val solution: String? = null,
    val impact: String? = null,
)
