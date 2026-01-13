package com.ravit.stash.controller

import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.domain.task.service.TaskService
import com.ravit.stash.shared.code.TaskType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskService: TaskService,
) {
    @GetMapping
    fun findAll(
        @RequestParam projectId: String?,
        @RequestParam type: TaskType?,
        @RequestParam keyword: String?,
    ): ResponseEntity<List<Task>> {
        val tasks =
            when {
                projectId != null -> taskService.findByProjectId(projectId)
                type != null -> taskService.findByType(type)
                keyword != null -> taskService.findByKeyword(keyword)
                else -> taskService.findAll()
            }
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ): ResponseEntity<Task> {
        val task = taskService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(task)
    }

    @PostMapping
    fun save(
        @RequestBody task: Task,
        @RequestParam(defaultValue = "false") extractKeywords: Boolean,
    ): ResponseEntity<Task> {
        val saved =
            if (extractKeywords) {
                taskService.saveWithKeywordExtraction(task)
            } else {
                taskService.save(task)
            }
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String,
    ): ResponseEntity<Unit> {
        taskService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
