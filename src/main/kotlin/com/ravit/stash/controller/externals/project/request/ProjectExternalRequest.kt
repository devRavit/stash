package com.ravit.stash.controller.externals.project.request

import com.ravit.stash.controller.externals.shared.request.PeriodExternalRequest
import com.ravit.stash.shared.code.CompanyType

data class ProjectExternalRequest(
    val id: String,
    val company: CompanyType,
    val name: String,
    val summary: String,
    val period: PeriodExternalRequest?,
    val techStack: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val teamSize: Int? = null,
    val role: String? = null,
)
