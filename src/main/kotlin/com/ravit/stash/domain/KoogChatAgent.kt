package com.ravit.stash.domain

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.model.PromptExecutor
import ai.koog.prompt.llm.LLModel

class KoogChatAgent(
    private val promptExecutor: PromptExecutor,
    private val llmModel: LLModel,
    private val systemPrompt: String,
    private val temperature: Double,
) : ChatAgent {
    override suspend fun chat(message: String): String {
        val aiAgent =
            AIAgent(
                promptExecutor = promptExecutor,
                llmModel = llmModel,
                systemPrompt = systemPrompt,
                temperature = temperature,
            )
        return aiAgent.run(message)
    }
}
