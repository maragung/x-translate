package com.xtranslate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SwapCalls
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xtranslate.core.nlp.TranslationRequest
import com.xtranslate.core.nlp.TranslationResult
import com.xtranslate.viewmodel.TranslateViewModel

@Composable
fun TranslateScreen(
  viewModel: TranslateViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header
    Text(
      text = "x-translate",
      style = MaterialTheme.typography.headlineLarge,
      modifier = Modifier.padding(bottom = 24.dp)
    )

    // Source and Target panels
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 200.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Source panel
      Card(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = uiState.sourceLang,
            style = MaterialTheme.typography.labelMedium
          )

          OutlinedTextField(
            value = uiState.sourceText,
            onValueChange = { viewModel.updateSourceText(it) },
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f),
            placeholder = { Text("Enter text...") },
            shape = RoundedCornerShape(8.dp)
          )

          Text(
            text = "${uiState.sourceText.length} characters",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
          )
        }
      }

      // Swap button
      Column(
        modifier = Modifier.align(Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        IconButton(
          onClick = { viewModel.swapLanguages() },
          modifier = Modifier.size(48.dp)
        ) {
          Icon(
            imageVector = Icons.Filled.SwapCalls,
            contentDescription = "Swap languages",
            tint = MaterialTheme.colorScheme.primary
          )
        }
      }

      // Target panel
      Card(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = uiState.targetLang,
            style = MaterialTheme.typography.labelMedium
          )

          SelectionContainer {
            Text(
              text = uiState.targetText.ifEmpty { "Translation will appear here..." },
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(4.dp)
            )
          }

          if (uiState.isLoading) {
            LinearProgressIndicator(
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      }
    }

    // Translate button
    Button(
      onClick = { viewModel.translate() },
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)
        .height(48.dp),
      enabled = uiState.sourceText.isNotEmpty() && !uiState.isLoading
    ) {
      if (uiState.isLoading) {
        CircularProgressIndicator(
          modifier = Modifier.size(24.dp),
          color = MaterialTheme.colorScheme.onPrimary,
          strokeWidth = 2.dp
        )
      } else {
        Text("Translate")
      }
    }

    // Action buttons
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = { viewModel.copyToClipboard() },
        modifier = Modifier.weight(1f),
        enabled = uiState.targetText.isNotEmpty()
      ) {
        Icon(Icons.Filled.ContentCopy, contentDescription = "Copy")
        Spacer(modifier = Modifier.width(4.dp))
        Text("Copy")
      }

      Button(
        onClick = { viewModel.share() },
        modifier = Modifier.weight(1f),
        enabled = uiState.targetText.isNotEmpty(),
        variant = ButtonVariant.Outlined
      ) {
        Icon(Icons.Filled.Share, contentDescription = "Share")
        Spacer(modifier = Modifier.width(4.dp))
        Text("Share")
      }
    }

    // Error display
    if (uiState.error != null) {
      Text(
        text = uiState.error!!,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 8.dp)
      )
    }
  }
}

enum class ButtonVariant {
  Filled, Outlined
}
