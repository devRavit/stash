package com.ravit.stash.controller

import com.ravit.stash.domain.project.document.Project
import com.ravit.stash.domain.project.service.ProjectService
import com.ravit.stash.shared.code.CompanyType
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
@RequestMapping("/api/projects")
class ProjectController(
    private val projectService: ProjectService,
) {
    @GetMapping
    fun findAll(
        @RequestParam company: CompanyType?,
        @RequestParam techStack: String?,
    ): ResponseEntity<List<Project>> {
        val projects =
            when {
                company != null -> projectService.findByCompany(company)
                techStack != null -> projectService.findByTechStack(techStack)
                else -> projectService.findAll()
            }
        return ResponseEntity.ok(projects)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: String,
    ): ResponseEntity<Project> {
        val project = projectService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(project)
    }

    @PostMapping
    fun save(
        @RequestBody project: Project,
    ): ResponseEntity<Project> {
        val saved = projectService.save(project)
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String,
    ): ResponseEntity<Unit> {
        projectService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
