package com.ravit.stash.domain.project.document

import com.ravit.stash.shared.code.CompanyType
import com.ravit.stash.shared.document.BaseDocument
import com.ravit.stash.shared.document.Period
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "projects")
class Project(
    @Indexed
    val company: CompanyType,
    val name: String,
    val summary: String,
    val period: Period?,
    val techStack: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val teamSize: Int? = null,
    val role: String? = null,
) : BaseDocument()
