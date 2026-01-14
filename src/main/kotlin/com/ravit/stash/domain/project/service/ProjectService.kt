package com.ravit.stash.domain.project.service

import com.ravit.stash.domain.project.document.Project
import com.ravit.stash.domain.project.repository.ProjectRepository
import com.ravit.stash.shared.code.CompanyType
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
) {
    fun findAll(): List<Project> = projectRepository.findAll()

    fun findById(id: ObjectId): Project? = projectRepository.findById(id).orElse(null)

    fun findByCompany(company: CompanyType): List<Project> = projectRepository.findByCompany(company)

    fun findByTechStack(techStack: String): List<Project> = projectRepository.findByTechStackContaining(techStack)

    fun save(project: Project): Project = projectRepository.save(project)

    fun deleteById(id: ObjectId) = projectRepository.deleteById(id)
}
