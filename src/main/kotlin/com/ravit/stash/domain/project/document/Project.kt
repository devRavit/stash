package com.ravit.stash.domain.project.document

import com.ravit.stash.shared.code.CompanyType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "projects")
data class Project(
    @Id
    val id: String,
    val company: CompanyType,
    val name: String,
    val summary: String,
    val period: ProjectPeriod,
    val techStack: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val teamSize: Int? = null,
    val role: String? = null,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
