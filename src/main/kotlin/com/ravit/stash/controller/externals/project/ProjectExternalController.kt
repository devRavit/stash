package com.ravit.stash.controller.externals.project

import com.ravit.stash.controller.externals.project.request.ProjectExternalRequest
import com.ravit.stash.controller.externals.project.response.ProjectExternalResponse
import com.ravit.stash.domain.project.document.Project
import com.ravit.stash.domain.project.service.ProjectService
import com.ravit.stash.domain.task.service.TaskService
import com.ravit.stash.shared.code.CompanyType
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
@RequestMapping("/externals/projects")
class ProjectExternalController(
    private val projectService: ProjectService,
    private val taskService: TaskService,
) {
    @GetMapping
    fun findAll(
        @RequestParam company: CompanyType?,
        @RequestParam techStack: String?,
        @RequestParam(defaultValue = "false") includeTasks: Boolean,
    ): ResponseEntity<List<ProjectExternalResponse>> {
        val projects =
            when {
                company != null -> projectService.findByCompany(company)
                techStack != null -> projectService.findByTechStack(techStack)
                else -> projectService.findAll()
            }
        val responses =
            if (includeTasks) {
                projects.map { project ->
                    val tasks = taskService.findByProjectId(project.id!!.toHexString())
                    ProjectExternalResponse.from(project, tasks)
                }
            } else {
                projects.map { ProjectExternalResponse.from(it) }
            }
        return ResponseEntity.ok(responses)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
        @RequestParam(defaultValue = "false") includeTasks: Boolean,
    ): ResponseEntity<ProjectExternalResponse> {
        val project = projectService.findById(ObjectId(id)) ?: return ResponseEntity.notFound().build()
        val response =
            if (includeTasks) {
                val tasks = taskService.findByProjectId(id)
                ProjectExternalResponse.from(project, tasks)
            } else {
                ProjectExternalResponse.from(project)
            }
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun save(
        @RequestBody request: ProjectExternalRequest,
    ): ResponseEntity<ProjectExternalResponse> {
        val project =
            Project(
                company = request.company,
                name = request.name,
                summary = request.summary,
                period = request.period?.toPeriod(),
                techStack = request.techStack ?: emptyList(),
                achievements = request.achievements ?: emptyList(),
                teamSize = request.teamSize,
                role = request.role,
            )
        val saved = projectService.save(project)
        return ResponseEntity.ok(ProjectExternalResponse.from(saved))
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String,
    ): ResponseEntity<Unit> {
        projectService.deleteById(ObjectId(id))
        return ResponseEntity.noContent().build()
    }
}
