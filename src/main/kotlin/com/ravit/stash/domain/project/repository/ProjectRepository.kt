package com.ravit.stash.domain.project.repository

import com.ravit.stash.domain.project.document.Project
import com.ravit.stash.shared.code.CompanyType
import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository : MongoRepository<Project, String> {
    fun findByCompany(company: CompanyType): List<Project>

    fun findByTechStackContaining(techStack: String): List<Project>
}
