package com.ravit.stash.configuration

import ai.koog.prompt.llm.LLMProvider
import ai.koog.prompt.llm.LLModel
import com.ravit.stash.ai.executor.GeminiPromptExecutor
import com.ravit.stash.domain.ChatAgent
import com.ravit.stash.domain.KoogChatAgent
import com.ravit.stash.infrastructure.gemini.GeminiClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AiProperties::class)
class AiConfiguration {
    @Bean
    fun chatAgent(
        properties: AiProperties,
        geminiClient: GeminiClient,
    ): ChatAgent {
        val executor = GeminiPromptExecutor(geminiClient = geminiClient)
        val llmModel =
            LLModel(
                provider = LLMProvider.Google,
                id = properties.model,
                capabilities = emptyList(),
                contextLength = 1000000L,
                maxOutputTokens = properties.maxTokens.toLong(),
            )
        return KoogChatAgent(
            promptExecutor = executor,
            llmModel = llmModel,
            systemPrompt = "당신은 도움이 되는 AI 어시스턴트입니다.",
            temperature = properties.temperature,
        )
    }
}
