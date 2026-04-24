package com.xtranslate.data.repository

import com.xtranslate.core.jni.NativeTranslator
import com.xtranslate.core.nlp.TranslationRequest
import com.xtranslate.core.nlp.TranslationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslationRepository @Inject constructor(
  private val nativeTranslator: NativeTranslator
) {

  suspend fun translate(request: TranslationRequest): TranslationResult =
    withContext(Dispatchers.Default) {
      val startTime = System.currentTimeMillis()

      val targetText = nativeTranslator.translate(request.sourceText)

      val latency = System.currentTimeMillis() - startTime

      TranslationResult(
        sourceText = request.sourceText,
        targetText = targetText,
        sourceLang = request.sourceLang,
        targetLang = request.targetLang,
        latencyMs = latency
      )
    }

  suspend fun translateBatch(requests: List<TranslationRequest>): List<TranslationResult> =
    withContext(Dispatchers.Default) {
      val sourceTexts = requests.map { it.sourceText }.toTypedArray()
      val targetTexts = nativeTranslator.translateBatch(sourceTexts)

      requests.zip(targetTexts) { request, targetText ->
        TranslationResult(
          sourceText = request.sourceText,
          targetText = targetText,
          sourceLang = request.sourceLang,
          targetLang = request.targetLang
        )
      }
    }
}
