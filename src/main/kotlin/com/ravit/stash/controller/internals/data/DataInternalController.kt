package com.ravit.stash.controller.internals.data

import com.ravit.stash.domain.project.repository.ProjectRepository
import com.ravit.stash.domain.task.repository.TaskRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internals/data")
class DataInternalController(
    private val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
) {
    @DeleteMapping("/all")
    fun deleteAll(): ResponseEntity<Map<String, Long>> {
        val projectCount = projectRepository.count()
        val taskCount = taskRepository.count()
        taskRepository.deleteAll()
        projectRepository.deleteAll()
        return ResponseEntity.ok(
            mapOf(
                "deletedProjects" to projectCount,
                "deletedTasks" to taskCount,
            ),
        )
    }

    @DeleteMapping("/projects")
    fun deleteAllProjects(): ResponseEntity<Map<String, Long>> {
        val count = projectRepository.count()
        projectRepository.deleteAll()
        return ResponseEntity.ok(mapOf("deletedProjects" to count))
    }

    @DeleteMapping("/tasks")
    fun deleteAllTasks(): ResponseEntity<Map<String, Long>> {
        val count = taskRepository.count()
        taskRepository.deleteAll()
        return ResponseEntity.ok(mapOf("deletedTasks" to count))
    }
}
