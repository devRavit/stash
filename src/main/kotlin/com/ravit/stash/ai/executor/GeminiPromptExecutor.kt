package com.ravit.stash.ai.executor

import ai.koog.agents.core.tools.ToolDescriptor
import ai.koog.prompt.dsl.ModerationResult
import ai.koog.prompt.dsl.Prompt
import ai.koog.prompt.executor.model.PromptExecutor
import ai.koog.prompt.llm.LLModel
import ai.koog.prompt.message.ContentPart
import ai.koog.prompt.message.Message
import ai.koog.prompt.message.ResponseMetaInfo
import ai.koog.prompt.streaming.StreamFrame
import com.ravit.stash.infrastructure.gemini.GeminiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.datetime.Clock

class GeminiPromptExecutor(
    private val geminiClient: GeminiClient,
) : PromptExecutor {
    override suspend fun execute(
        prompt: Prompt,
        model: LLModel,
        tools: List<ToolDescriptor>,
    ): List<Message.Response> {
        val content = prompt.messages.joinToString("\n") { it.content }
        val responseText = geminiClient.generateContent(model.id, content)

        val metaInfo =
            ResponseMetaInfo.create(
                clock = Clock.System,
            )

        return listOf(
            Message.Assistant(
                part = ContentPart.Text(responseText),
                metaInfo = metaInfo,
            ),
        )
    }

    override fun executeStreaming(
        prompt: Prompt,
        model: LLModel,
        tools: List<ToolDescriptor>,
    ): Flow<StreamFrame> = emptyFlow()

    override suspend fun moderate(
        prompt: Prompt,
        model: LLModel,
    ): ModerationResult = ModerationResult(isHarmful = false, categories = emptyMap())

    override fun close() {}
}
