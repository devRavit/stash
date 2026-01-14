package com.ravit.stash.controller.externals.project.response

import com.ravit.stash.domain.project.document.Project
import com.ravit.stash.shared.code.CompanyType
import java.time.LocalDateTime

data class ProjectExternalResponse(
    val id: String,
    val company: CompanyType,
    val name: String,
    val summary: String,
    val period: ProjectPeriodExternalResponse,
    val techStack: List<String>,
    val achievements: List<String>,
    val teamSize: Int?,
    val role: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(project: Project): ProjectExternalResponse =
            ProjectExternalResponse(
                id = project.id,
                company = project.company,
                name = project.name,
                summary = project.summary,
                period =
                    ProjectPeriodExternalResponse(
                        startedAt = project.period.startedAt,
                        endedAt = project.period.endedAt,
                    ),
                techStack = project.techStack,
                achievements = project.achievements,
                teamSize = project.teamSize,
                role = project.role,
                createdAt = project.createdAt,
                updatedAt = project.updatedAt,
            )
    }
}
