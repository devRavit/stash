package com.ravit.stash.domain.task.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ravit.stash.domain.task.document.Task
import com.ravit.stash.infrastructure.gemini.GeminiClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TaskKeywordExtractor(
    private val geminiClient: GeminiClient,
) {
    private val logger = LoggerFactory.getLogger(TaskKeywordExtractor::class.java)
    private val objectMapper = jacksonObjectMapper()

    suspend fun extractKeywords(task: Task): List<String> {
        val prompt = buildPrompt(task)

        return try {
            val response = geminiClient.generateContent("gemini-2.0-flash", prompt)
            parseKeywords(response)
        } catch (e: Exception) {
            logger.error("Failed to extract keywords for task: ${task.id}", e)
            emptyList()
        }
    }

    private fun buildPrompt(task: Task): String =
        """
        다음 작업 내용에서 검색용 핵심 키워드를 5-10개 추출해줘.
        기술 용어, 도메인 용어, 문제 해결 패턴 위주로 추출해.

        제목: ${task.title}
        설명: ${task.description ?: "없음"}
        배경: ${task.details?.background ?: "없음"}
        해결방법: ${task.details?.solution ?: "없음"}
        성과: ${task.details?.impact ?: "없음"}

        JSON 배열 형식으로만 응답해줘. 다른 설명 없이 배열만.
        예시: ["키워드1", "키워드2", "키워드3"]
        """.trimIndent()

    private fun parseKeywords(response: String): List<String> =
        try {
            val cleaned =
                response
                    .trim()
                    .removePrefix("```json")
                    .removePrefix("```")
                    .removeSuffix("```")
                    .trim()
            objectMapper.readValue<List<String>>(cleaned)
        } catch (e: Exception) {
            logger.warn("Failed to parse keywords from response: $response", e)
            emptyList()
        }
}
