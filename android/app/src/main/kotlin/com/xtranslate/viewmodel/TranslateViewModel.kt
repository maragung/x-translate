package com.xtranslate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtranslate.core.nlp.TranslationRequest
import com.xtranslate.data.repository.TranslationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class TranslateUiState(
  val sourceText: String = "",
  val targetText: String = "",
  val sourceLang: String = "English",
  val targetLang: String = "Indonesian",
  val isLoading: Boolean = false,
  val error: String? = null
)

@HiltViewModel
class TranslateViewModel @Inject constructor(
  private val repository: TranslationRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(TranslateUiState())
  val uiState: StateFlow<TranslateUiState> = _uiState.asStateFlow()

  fun updateSourceText(text: String) {
    _uiState.value = _uiState.value.copy(sourceText = text)
  }

  fun swapLanguages() {
    val current = _uiState.value
    _uiState.value = current.copy(
      sourceLang = current.targetLang,
      targetLang = current.sourceLang,
      sourceText = current.targetText,
      targetText = current.sourceText
    )
  }

  fun translate() {
    val current = _uiState.value
    if (current.sourceText.isEmpty()) return

    viewModelScope.launch {
      _uiState.value = current.copy(isLoading = true, error = null)

      try {
        val request = TranslationRequest(
          sourceText = current.sourceText,
          sourceLang = current.sourceLang.lowercase().take(2),
          targetLang = current.targetLang.lowercase().take(2)
        )

        val result = repository.translate(request)
        _uiState.value = current.copy(
          targetText = result.targetText,
          isLoading = false
        )

        Timber.d("Translation successful: ${result.latencyMs}ms")
      } catch (e: Exception) {
        Timber.e(e, "Translation failed")
        _uiState.value = current.copy(
          isLoading = false,
          error = "Translation failed: ${e.message}"
        )
      }
    }
  }

  fun copyToClipboard() {
    // TODO: Implement clipboard copy
    Timber.d("Copy to clipboard: ${_uiState.value.targetText}")
  }

  fun share() {
    // TODO: Implement share
    Timber.d("Share translation: ${_uiState.value.targetText}")
  }
}
