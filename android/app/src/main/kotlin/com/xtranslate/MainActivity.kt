package com.xtranslate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.google.dagger.hilt.android.AndroidEntryPoint
import com.xtranslate.ui.screens.TranslateScreen
import com.xtranslate.ui.theme.XTranslateTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      XTranslateTheme {
        Surface(
          color = MaterialTheme.colorScheme.background
        ) {
          TranslateScreen()
        }
      }
    }
  }
}
