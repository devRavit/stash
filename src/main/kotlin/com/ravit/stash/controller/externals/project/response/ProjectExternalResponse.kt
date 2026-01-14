package com.ravit.stash.controller.externals.project.response

import com.ravit.stash.controller.externals.shared.response.PeriodExternalResponse
import com.ravit.stash.domain.project.document.Project
import com.ravit.stash.shared.annotation.WithKstZoneTime
import com.ravit.stash.shared.code.CompanyType
import java.time.LocalDateTime

data class ProjectExternalResponse(
    val id: String,
    val company: CompanyType,
    val name: String,
    val summary: String,
    val period: PeriodExternalResponse?,
    val techStack: List<String>,
    val achievements: List<String>,
    val teamSize: Int?,
    val role: String?,
    @WithKstZoneTime
    val createdAt: LocalDateTime,
    @WithKstZoneTime
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(project: Project): ProjectExternalResponse =
            ProjectExternalResponse(
                id = requireNotNull(project.id) { "Project id must not be null after save" }.toHexString(),
                company = project.company,
                name = project.name,
                summary = project.summary,
                period = project.period?.let { PeriodExternalResponse.from(it) },
                techStack = project.techStack,
                achievements = project.achievements,
                teamSize = project.teamSize,
                role = project.role,
                createdAt = requireNotNull(project.createdAt) { "Project createdAt must not be null" },
                updatedAt = requireNotNull(project.updatedAt) { "Project updatedAt must not be null" },
            )
    }
}
