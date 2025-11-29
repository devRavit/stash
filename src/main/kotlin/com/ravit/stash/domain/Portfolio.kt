package com.ravit.stash.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "portfolio")
data class Portfolio(
    @Id
    val id: ObjectId = ObjectId.get(),
    val company: CompanyType,
    val projectName: String,
    val title: String,
    val summary: String,
    val period: Period,
    val techStack: List<String> = emptyList(),
    val systemDesign: SystemDesign? = null,
    val performance: Performance? = null,
    val problemSolving: List<ProblemSolving> = emptyList(),
    val keyAchievements: List<String> = emptyList(),
    val teamSize: Int? = null,
    val role: String? = null,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)

data class Period(
    val startDate: LocalDate,
    val endDate: LocalDate?,
)

data class SystemDesign(
    val architecture: String?,
    val scalability: String?,
    val dataFlow: String?,
    val diagram: String?,
)

data class Performance(
    val metrics: List<PerformanceMetric> = emptyList(),
    val optimizations: List<String> = emptyList(),
)

data class PerformanceMetric(
    val name: String,
    val before: String?,
    val after: String,
    val improvement: String?,
)

data class ProblemSolving(
    val problem: String,
    val analysis: String?,
    val solution: String,
    val result: String?,
)
