package com.xtranslate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "history")
data class HistoryEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val sourceText: String,
  val targetText: String,
  val sourceLang: String,
  val targetLang: String,
  val style: String = "formal",
  val timestamp: Long = System.currentTimeMillis(),
  val tags: String = ""  // JSON array
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val sourceText: String,
  val targetText: String,
  val sourceLang: String,
  val targetLang: String,
  val createdAt: Long = System.currentTimeMillis(),
  val folder: String = "default"
)

data class TranslationRecord(
  val id: Long,
  val sourceText: String,
  val targetText: String,
  val sourceLang: String,
  val targetLang: String,
  val timestamp: Long,
  val isBookmarked: Boolean = false
)
