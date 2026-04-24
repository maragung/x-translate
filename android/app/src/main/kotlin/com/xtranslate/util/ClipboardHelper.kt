package com.xtranslate.util

import android.content.Context
import android.content.ClipboardManager
import android.content.ClipData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClipboardHelper @Inject constructor(
  @ApplicationContext private val context: Context
) {

  fun copyToClipboard(text: String, label: String = "x-translate") {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)
  }

  fun getFromClipboard(): String? {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = clipboard.primaryClip
    return clip?.getItemAt(0)?.text?.toString()
  }
}
