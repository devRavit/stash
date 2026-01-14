package com.ravit.stash.domain.task.repository

import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.shared.code.TaskType
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, ObjectId> {
    fun findByProjectId(projectId: String): List<Task>

    fun findByType(type: TaskType): List<Task>

    fun findByKeywordsContaining(keyword: String): List<Task>

    fun findByProjectIdAndType(
        projectId: String,
        type: TaskType,
    ): List<Task>
}
