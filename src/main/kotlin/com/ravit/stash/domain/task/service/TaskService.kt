package com.ravit.stash.domain.task.service

import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.domain.task.repository.TaskRepository
import com.ravit.stash.shared.code.TaskType
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val taskKeywordExtractor: TaskKeywordExtractor,
) {
    fun findAll(): List<Task> = taskRepository.findAll()

    fun findById(id: ObjectId): Task? = taskRepository.findById(id).orElse(null)

    fun findByProjectId(projectId: String): List<Task> = taskRepository.findByProjectId(projectId)

    fun findByType(type: TaskType): List<Task> = taskRepository.findByType(type)

    fun findByKeyword(keyword: String): List<Task> = taskRepository.findByKeywordsContaining(keyword)

    fun save(task: Task): Task = taskRepository.save(task)

    fun saveWithKeywordExtraction(task: Task): Task {
        val keywords =
            runBlocking {
                taskKeywordExtractor.extractKeywords(task)
            }
        task.updateKeywords(keywords)
        return taskRepository.save(task)
    }

    fun deleteById(id: ObjectId) = taskRepository.deleteById(id)
}
