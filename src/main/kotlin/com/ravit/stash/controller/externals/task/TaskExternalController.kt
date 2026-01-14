package com.ravit.stash.controller.externals.task

import com.ravit.stash.controller.externals.task.request.TaskExternalRequest
import com.ravit.stash.controller.externals.task.response.TaskExternalResponse
import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.domain.task.service.TaskService
import com.ravit.stash.shared.code.TaskType
import org.bson.types.ObjectId
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
@RequestMapping("/externals/tasks")
class TaskExternalController(
    private val taskService: TaskService,
) {
    @GetMapping
    fun findAll(
        @RequestParam projectId: String?,
        @RequestParam type: TaskType?,
        @RequestParam keyword: String?,
    ): ResponseEntity<List<TaskExternalResponse>> {
        val tasks =
            when {
                projectId != null -> taskService.findByProjectId(projectId)
                type != null -> taskService.findByType(type)
                keyword != null -> taskService.findByKeyword(keyword)
                else -> taskService.findAll()
            }
        return ResponseEntity.ok(tasks.map { TaskExternalResponse.from(it) })
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ): ResponseEntity<TaskExternalResponse> {
        val task = taskService.findById(ObjectId(id)) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(TaskExternalResponse.from(task))
    }

    @PostMapping
    fun save(
        @RequestBody request: TaskExternalRequest,
        @RequestParam(defaultValue = "false") extractKeywords: Boolean,
    ): ResponseEntity<TaskExternalResponse> {
        val task =
            Task(
                projectId = request.projectId,
                type = request.type,
                title = request.title,
                description = request.description,
                period = request.period?.toPeriod(),
                workingDays = request.workingDays,
                details = request.details?.toTaskDetails(),
                keywords = request.keywords,
            )
        val saved =
            if (extractKeywords) {
                taskService.saveWithKeywordExtraction(task)
            } else {
                taskService.save(task)
            }
        return ResponseEntity.ok(TaskExternalResponse.from(saved))
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String,
    ): ResponseEntity<Unit> {
        taskService.deleteById(ObjectId(id))
        return ResponseEntity.noContent().build()
    }
}
