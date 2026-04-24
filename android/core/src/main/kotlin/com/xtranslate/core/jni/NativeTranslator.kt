package com.xtranslate.core.jni

class NativeTranslator {
  companion object {
    init {
      try {
        System.loadLibrary("x-translate-core")
      } catch (e: UnsatisfiedLinkError) {
        throw RuntimeException("Failed to load native library: ${e.message}")
      }
    }
  }

  external fun initializeModel(modelPath: String): Boolean
  external fun translate(sourceText: String): String
  external fun translateBatch(sourceTexts: Array<String>): Array<String>
  external fun setDecoderConfig(configJson: String): Boolean
  external fun clearCache(): Unit
  external fun getMemoryUsage(): Long
  external fun shutdown(): Unit
}
