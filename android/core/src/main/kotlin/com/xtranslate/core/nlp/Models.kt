package com.xtranslate.core.nlp

data class TranslationRequest(
  val sourceText: String,
  val sourceLang: String = "en",
  val targetLang: String = "id",
  val modelVariant: String = "balanced",
  val style: String = "formal",
  val temperature: Float = 1.0f,
  val topK: Int = 40,
  val topP: Float = 0.9f,
  val beamWidth: Int = 4
)

data class TranslationResult(
  val sourceText: String,
  val targetText: String,
  val sourceLang: String,
  val targetLang: String,
  val confidence: Float = 1.0f,
  val latencyMs: Long = 0,
  val modelUsed: String = "balanced"
)

data class DecoderConfig(
  val temperature: Float = 1.0f,
  val topK: Int = 40,
  val topP: Float = 0.9f,
  val repetitionPenalty: Float = 1.2f,
  val beamWidth: Int = 4,
  val lengthPenalty: Float = 0.6f,
  val earlyStop: Boolean = true
)

enum class ModelVariant(val sizeMb: Int, val latencyMs: Int) {
  LITE(45, 150),
  BALANCED(95, 350),
  ACCURATE(190, 700)
}

enum class TranslationStyle {
  FORMAL,
  CASUAL,
  LITERAL,
  CONTEXTUAL,
  BUSINESS,
  ACADEMIC,
  TECHNICAL
}
