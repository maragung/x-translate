# x-translate: Blueprint Komprehensif

**Status**: Draft Blueprint | **Versi**: 1.0 | **Tgl Update**: April 2026

---

## 📋 Daftar Isi
1. [Visi & Tujuan](#visi--tujuan)
2. [Struktur Proyek Fisik](#struktur-proyek-fisik)
3. [Arsitektur Aplikasi](#arsitektur-aplikasi)
4. [Core Engine NLP](#core-engine-nlp)
5. [Design System Modern](#design-system-modern)
6. [Fitur Lengkap Produksi](#fitur-lengkap-produksi)
7. [Implementasi Teknis](#implementasi-teknis)
8. [QA & Testing](#qa--testing)
9. [CI/CD Pipeline](#cicd-pipeline)
10. [Build & Release](#build--release)
11. [Roadmap Rinci](#roadmap-rinci)
12. [Checklist Progres](#checklist-progres)
13. [Metrik Performa](#metrik-performa)
14. [Risk & Mitigation](#risk--mitigation)
15. [Changelog Terstruktur](#changelog-terstruktur)

---

## 🎯 Visi & Tujuan

### Visi
Aplikasi translator lintas platform (Android & Desktop) dengan teknologi NLP terdepan, offload inference lokal penuh, desain UI/UX modern, dan performa tinggi siap rilis komersial.

### Tujuan Utama
- ✅ Dual platform: Android (Kotlin + C++ JNI) & Desktop (C++ Qt/QML)
- ✅ Offline penuh tanpa koneksi internet
- ✅ Multi-bahasa: EN↔ID, AR↔ID, EN↔ZH (Simp/Trad + Pinyin)
- ✅ Performa latensi < 500ms per sentence
- ✅ Ukuran aplikasi < 200MB (Android dengan split ABI)
- ✅ BLEU score > 0.75 untuk domain umum
- ✅ UI/UX konsisten, responsif, aksesbel penuh
- ✅ Production-ready dengan crash handling, logging, update incremental model

---

## 📁 Struktur Proyek Fisik

```
x-translate/
├── android/                          # Platform Android (Kotlin + C++ JNI)
│   ├── app/                         # Module aplikasi utama
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── kotlin/com/xtranslate/
│   │   │   │   │   ├── MainActivity.kt
│   │   │   │   │   ├── ui/
│   │   │   │   │   │   ├── screens/
│   │   │   │   │   │   │   ├── TranslateScreen.kt
│   │   │   │   │   │   │   ├── HistoryScreen.kt
│   │   │   │   │   │   │   ├── SettingsScreen.kt
│   │   │   │   │   │   │   ├── DictionaryScreen.kt
│   │   │   │   │   │   │   └── LearningModeScreen.kt
│   │   │   │   │   │   ├── components/
│   │   │   │   │   │   │   ├── TranslateCard.kt
│   │   │   │   │   │   │   ├── LanguagePicker.kt
│   │   │   │   │   │   │   ├── ModelSelector.kt
│   │   │   │   │   │   │   └── HistoryItem.kt
│   │   │   │   │   │   └── theme/
│   │   │   │   │   │       ├── Color.kt
│   │   │   │   │   │       ├── Type.kt
│   │   │   │   │   │       ├── Theme.kt
│   │   │   │   │   │       └── Dimension.kt
│   │   │   │   │   ├── viewmodel/
│   │   │   │   │   │   ├── TranslateViewModel.kt
│   │   │   │   │   │   ├── HistoryViewModel.kt
│   │   │   │   │   │   ├── SettingsViewModel.kt
│   │   │   │   │   │   └── DictionaryViewModel.kt
│   │   │   │   │   ├── data/
│   │   │   │   │   │   ├── repository/
│   │   │   │   │   │   │   ├── TranslationRepository.kt
│   │   │   │   │   │   │   ├── HistoryRepository.kt
│   │   │   │   │   │   │   └── DictionaryRepository.kt
│   │   │   │   │   │   ├── local/
│   │   │   │   │   │   │   ├── TranslateDatabase.kt
│   │   │   │   │   │   │   ├── dao/
│   │   │   │   │   │   │   │   ├── HistoryDao.kt
│   │   │   │   │   │   │   │   └── BookmarkDao.kt
│   │   │   │   │   │   │   └── entity/
│   │   │   │   │   │   │       ├── HistoryEntity.kt
│   │   │   │   │   │   │       └── BookmarkEntity.kt
│   │   │   │   │   │   └── model/
│   │   │   │   │   │       ├── TranslationRequest.kt
│   │   │   │   │   │       └── TranslationResult.kt
│   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── ClipboardMonitorService.kt
│   │   │   │   │   │   ├── ModelManager.kt
│   │   │   │   │   │   ├── LanguageDetector.kt
│   │   │   │   │   │   ├── TextToSpeechService.kt
│   │   │   │   │   │   └── SpeechToTextService.kt
│   │   │   │   │   ├── util/
│   │   │   │   │   │   ├── Logger.kt
│   │   │   │   │   │   ├── CrashHandler.kt
│   │   │   │   │   │   ├── FileHelper.kt
│   │   │   │   │   │   └── PermissionHelper.kt
│   │   │   │   │   └── App.kt
│   │   │   │   ├── AndroidManifest.xml
│   │   │   │   └── res/
│   │   │   │       ├── values/
│   │   │   │       │   ├── strings.xml
│   │   │   │       │   ├── colors.xml
│   │   │   │       │   ├── dimens.xml
│   │   │   │       │   └── styles.xml
│   │   │   │       ├── drawable/
│   │   │   │       ├── mipmap/
│   │   │   │       └── raw/
│   │   │   ├── test/
│   │   │   │   └── kotlin/com/xtranslate/
│   │   │   │       ├── viewmodel/
│   │   │   │       └── repository/
│   │   │   └── androidTest/
│   │   │       └── kotlin/com/xtranslate/
│   │   │           ├── ui/
│   │   │           └── integration/
│   │   ├── build.gradle.kts
│   │   └── proguard-rules.pro
│   │
│   ├── core/                         # Module shared logic & JNI wrapper
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── kotlin/com/xtranslate/core/
│   │   │   │   │   ├── nli/
│   │   │   │   │   │   ├── NLPEngine.kt
│   │   │   │   │   │   ├── OnnxTranslator.kt
│   │   │   │   │   │   ├── TFLiteTranslator.kt
│   │   │   │   │   │   ├── ModelCache.kt
│   │   │   │   │   │   └── DecoderConfig.kt
│   │   │   │   │   ├── jni/
│   │   │   │   │   │   └── NativeTranslator.kt
│   │   │   │   │   ├── tokenizer/
│   │   │   │   │   │   ├── Tokenizer.kt
│   │   │   │   │   │   └── VocabularyLoader.kt
│   │   │   │   │   ├── cache/
│   │   │   │   │   │   ├── LruCache.kt
│   │   │   │   │   │   └── DiskCache.kt
│   │   │   │   │   └── config/
│   │   │   │   │       ├── ModelConfig.kt
│   │   │   │   │       └── LanguageConfig.kt
│   │   │   │   ├── jni/
│   │   │   │   │   └── CMakeLists.txt
│   │   │   │   └── cpp/
│   │   │   │       ├── CMakeLists.txt
│   │   │   │       ├── include/
│   │   │   │       │   ├── translator.h
│   │   │   │       │   ├── tokenizer.h
│   │   │   │       │   ├── model_manager.h
│   │   │   │       │   └── utils.h
│   │   │   │       └── src/
│   │   │   │           ├── translator.cpp
│   │   │   │           ├── tokenizer.cpp
│   │   │   │           ├── model_manager.cpp
│   │   │   │           ├── jni_wrapper.cpp
│   │   │   │           ├── logger.cpp
│   │   │   │           └── memory_pool.cpp
│   │   │   └── test/
│   │   │       └── kotlin/com/xtranslate/core/
│   │   │           ├── nli/
│   │   │           └── cache/
│   │   └── build.gradle.kts
│   │
│   ├── feature/                      # Feature modules (optional plugins)
│   │   ├── feature-ocr/
│   │   │   ├── src/main/kotlin/
│   │   │   └── build.gradle.kts
│   │   ├── feature-batch/
│   │   │   ├── src/main/kotlin/
│   │   │   └── build.gradle.kts
│   │   └── feature-learning/
│   │       ├── src/main/kotlin/
│   │       └── build.gradle.kts
│   │
│   ├── build-logic/                  # Build conventions & plugins
│   │   ├── src/main/kotlin/
│   │   │   └── com/xtranslate/buildlogic/
│   │   │       ├── AndroidAppConventionPlugin.kt
│   │   │       ├── AndroidLibraryConventionPlugin.kt
│   │   │       └── KotlinConventionPlugin.kt
│   │   └── build.gradle.kts
│   │
│   ├── gradle/
│   │   ├── libs.versions.toml
│   │   └── wrapper/
│   │
│   ├── build.gradle.kts              # Root build configuration
│   ├── settings.gradle.kts
│   ├── local.properties.template
│   ├── CMakeLists.txt                # CMake untuk native builds
│   ├── gradle.properties
│   ├── build-scripts/                # Automated build scripts
│   │   ├── build.sh
│   │   ├── build-release.sh
│   │   ├── sign-apk.sh
│   │   └── download-models.sh
│   └── README.md
│
├── desktop/                           # Platform Desktop (C++ Qt/QML)
│   ├── src/
│   │   ├── ui/
│   │   │   ├── main_window.h
│   │   │   ├── main_window.cpp
│   │   │   ├── screens/
│   │   │   │   ├── translate_screen.h
│   │   │   │   ├── translate_screen.cpp
│   │   │   │   ├── history_screen.h
│   │   │   │   ├── history_screen.cpp
│   │   │   │   ├── settings_screen.h
│   │   │   │   ├── settings_screen.cpp
│   │   │   │   └── dictionary_screen.h
│   │   │   ├── dialogs/
│   │   │   │   ├── model_manager_dialog.h
│   │   │   │   ├── model_manager_dialog.cpp
│   │   │   │   ├── batch_translator_dialog.h
│   │   │   │   └── batch_translator_dialog.cpp
│   │   │   ├── widgets/
│   │   │   │   ├── translate_widget.h
│   │   │   │   ├── language_selector.h
│   │   │   │   ├── history_panel.h
│   │   │   │   └── model_indicator.h
│   │   │   ├── qml/
│   │   │   │   ├── main.qml
│   │   │   │   ├── screens/
│   │   │   │   │   ├── TranslateScreen.qml
│   │   │   │   │   ├── HistoryScreen.qml
│   │   │   │   │   └── SettingsScreen.qml
│   │   │   │   ├── components/
│   │   │   │   │   ├── TranslateCard.qml
│   │   │   │   │   ├── Button.qml
│   │   │   │   │   ├── TextInput.qml
│   │   │   │   │   ├── ComboBox.qml
│   │   │   │   │   └── HistoryList.qml
│   │   │   │   └── theme/
│   │   │   │       ├── Colors.qml
│   │   │   │       ├── Fonts.qml
│   │   │   │       ├── Sizes.qml
│   │   │   │       └── Theme.qml
│   │   │   └── style/
│   │   │       └── style.qss
│   │   │
│   │   ├── core/
│   │   │   ├── nlp_engine.h
│   │   │   ├── nlp_engine.cpp
│   │   │   ├── onnx_translator.h
│   │   │   ├── onnx_translator.cpp
│   │   │   ├── tflite_translator.h
│   │   │   ├── tflite_translator.cpp
│   │   │   ├── model_manager.h
│   │   │   ├── model_manager.cpp
│   │   │   ├── language_detector.h
│   │   │   ├── language_detector.cpp
│   │   │   ├── tokenizer.h
│   │   │   ├── tokenizer.cpp
│   │   │   ├── cache_manager.h
│   │   │   └── cache_manager.cpp
│   │   │
│   │   ├── models/
│   │   │   ├── translation_model.h
│   │   │   ├── translation_model.cpp
│   │   │   ├── language_model.h
│   │   │   ├── ocr_model.h
│   │   │   ├── language_detector_model.h
│   │   │   └── model_loader.h
│   │   │
│   │   ├── service/
│   │   │   ├── clipboard_monitor.h
│   │   │   ├── clipboard_monitor.cpp
│   │   │   ├── text_to_speech.h
│   │   │   ├── text_to_speech.cpp
│   │   │   ├── file_handler.h
│   │   │   ├── file_handler.cpp
│   │   │   ├── batch_translator.h
│   │   │   ├── batch_translator.cpp
│   │   │   ├── ocr_service.h
│   │   │   └── ocr_service.cpp
│   │   │
│   │   ├── util/
│   │   │   ├── logger.h
│   │   │   ├── logger.cpp
│   │   │   ├── crash_handler.h
│   │   │   ├── memory_pool.h
│   │   │   ├── thread_pool.h
│   │   │   ├── lru_cache.h
│   │   │   ├── performance_monitor.h
│   │   │   └── file_utils.h
│   │   │
│   │   ├── data/
│   │   │   ├── database.h
│   │   │   ├── database.cpp
│   │   │   ├── dao/
│   │   │   │   ├── history_dao.h
│   │   │   │   ├── bookmark_dao.h
│   │   │   │   └── settings_dao.h
│   │   │   └── entity/
│   │   │       ├── history_entity.h
│   │   │       └── bookmark_entity.h
│   │   │
│   │   ├── main.cpp
│   │   └── app.h
│   │
│   ├── test/
│   │   ├── unit/
│   │   │   ├── CMakeLists.txt
│   │   │   ├── test_nlp_engine.cpp
│   │   │   ├── test_tokenizer.cpp
│   │   │   ├── test_cache_manager.cpp
│   │   │   └── test_model_loader.cpp
│   │   └── integration/
│   │       ├── CMakeLists.txt
│   │       ├── test_translation_pipeline.cpp
│   │       └── test_batch_translator.cpp
│   │
│   ├── resources/
│   │   ├── models/
│   │   │   ├── en-id-lite.onnx
│   │   │   ├── en-id-balanced.onnx
│   │   │   ├── en-id-accurate.onnx
│   │   │   ├── ar-id-lite.onnx
│   │   │   ├── en-zh-lite.onnx
│   │   │   └── language-detect.onnx
│   │   ├── dictionaries/
│   │   │   ├── en-id.db
│   │   │   └── ar-id.db
│   │   ├── icons/
│   │   ├── screenshots/
│   │   └── fonts/
│   │
│   ├── CMakeLists.txt                # Root CMake config
│   ├── CMakePresets.json             # CMake presets untuk build variants
│   ├── conanfile.txt                 # Dependency management (Conan)
│   ├── vcpkg.json                    # Alternative: vcpkg manifest
│   │
│   ├── build-scripts/
│   │   ├── build.sh                  # Linux build
│   │   ├── build-win.bat             # Windows MSVC build
│   │   ├── build-mingw.sh            # Windows MinGW build
│   │   ├── build-release.sh          # Release build
│   │   ├── create-installer.bat      # NSIS installer (Windows)
│   │   ├── create-appimage.sh        # AppImage (Linux)
│   │   └── download-models.sh
│   │
│   ├── .github/
│   │   └── workflows/
│   │       ├── build-windows.yml
│   │       ├── build-linux.yml
│   │       ├── test.yml
│   │       └── release.yml
│   │
│   ├── installer/
│   │   ├── nsis/
│   │   │   ├── installer.nsi         # NSIS script (Windows)
│   │   │   └── license.txt
│   │   ├── innosetup/
│   │   │   ├── installer.iss         # Inno Setup (Windows)
│   │   │   └── license.txt
│   │   └── linux/
│   │       ├── x-translate.desktop   # .desktop file
│   │       ├── x-translate.appdata.xml
│   │       └── postinst               # dpkg postinst script
│   │
│   ├── gradle.properties             # (untuk Qt/CMake Java tests jika ada)
│   ├── README.md
│   └── DEVELOP.md
│
├── docs/
│   ├── ARCHITECTURE.md               # Dokumentasi arsitektur lengkap
│   ├── NLP_ENGINE.md                # Detail core NLP engine
│   ├── UI_DESIGN_SYSTEM.md          # Design system specification
│   ├── API_REFERENCE.md
│   ├── DEPLOYMENT.md                # Panduan deployment & release
│   ├── CONTRIBUTION.md
│   └── TROUBLESHOOTING.md
│
├── shared/                           # Shared resources & utilities
│   ├── models/
│   │   └── (symlink/copy dari android/src/main/assets & desktop/resources/models)
│   ├── test-data/
│   │   ├── test-sentences.json
│   │   └── benchmark-data.csv
│   └── scripts/
│       ├── download-onnx-models.py
│       ├── convert-models.py
│       ├── quantize-models.py
│       └── validate-models.py
│
├── .github/
│   ├── workflows/
│   │   ├── ci.yml                   # CI untuk seluruh repo
│   │   ├── android-build.yml
│   │   ├── desktop-build.yml
│   │   ├── test.yml
│   │   ├── release.yml
│   │   └── changelog-generate.yml
│   │
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md
│   │   └── feature_request.md
│   │
│   └── pull_request_template.md
│
├── .gitignore
├── .editorconfig
├── CHANGELOG.md                      # Changelog terstruktur
├── LICENSE
├── README.md                         # Project overview
├── CONTRIBUTING.md
├── plan.md                           # 📄 Blueprint ini
└── VERSION                           # Semantic versioning

```

---

## 🏗️ Arsitektur Aplikasi

### 1. Clean Architecture dengan Layering

```
┌─────────────────────────────────────────────┐
│         PRESENTATION LAYER (UI)             │
│  ┌───────────────────────────────────────┐  │
│  │ Screens (Compose/QML) | ViewModels    │  │
│  │ Components | Theme | Navigation       │  │
│  └───────────────────────────────────────┘  │
└──────────────┬──────────────────────────────┘
               │
┌──────────────▼──────────────────────────────┐
│         SERVICE LAYER                       │
│  ┌───────────────────────────────────────┐  │
│  │ ModelManager | LanguageDetector       │  │
│  │ ClipboardMonitor | TextToSpeech       │  │
│  │ BatchTranslator | OCRService          │  │
│  │ FileHandler | PaymentValidator        │  │
│  └───────────────────────────────────────┘  │
└──────────────┬──────────────────────────────┘
               │
┌──────────────▼──────────────────────────────┐
│         DOMAIN LAYER                        │
│  ┌───────────────────────────────────────┐  │
│  │ UseCases | Repositories (interfaces)  │  │
│  │ Domain Models | Domain Exceptions     │  │
│  └───────────────────────────────────────┘  │
└──────────────┬──────────────────────────────┘
               │
┌──────────────▼──────────────────────────────┐
│         DATA LAYER                          │
│  ┌───────────────────────────────────────┐  │
│  │ Repository Implementations            │  │
│  │ Local: Room (Android) / SQLite (Qt)   │  │
│  │ Cache: LRU Memory + Disk Cache        │  │
│  │ Models: ONNX/TFLite Loading           │  │
│  └───────────────────────────────────────┘  │
└──────────────┬──────────────────────────────┘
               │
┌──────────────▼──────────────────────────────┐
│    CORE INFERENCE ENGINE (JNI / Native)     │
│  ┌───────────────────────────────────────┐  │
│  │ NLPEngine (ONNX Runtime / TFLite)      │  │
│  │ Tokenizer | Vocabulary Manager        │  │
│  │ BatchProcessor | Decoder Strategies   │  │
│  │ Memory Pool | Thread Pool Manager     │  │
│  └───────────────────────────────────────┘  │
└─────────────────────────────────────────────┘
```

### 2. Module Dependencies (Android Gradle)

```
app/ (depends on)
├── core/ (depends on)
│   ├── ONNX Runtime, TFLite
│   └── Standard Library
├── feature-ocr/
├── feature-batch/
└── feature-learning/

build-logic/ (shared build conventions)
```

### 3. Component Interaction Flow

```
User Input (Text/Voice/Image)
           ↓
    ┌──────────────────┐
    │ Input Processor  │ (DetectLanguage, Validate)
    └────────┬─────────┘
             ↓
    ┌──────────────────────┐
    │ ModelManager         │ (Select model, Load weights)
    │ (Load/Cache Mgmt)    │
    └────────┬─────────────┘
             ↓
    ┌──────────────────────┐
    │ Tokenizer            │ (Encode input to tokens)
    └────────┬─────────────┘
             ↓
    ┌──────────────────────────┐
    │ NLPEngine.Infer()        │ (Batch inference, streaming)
    │ (ONNX Runtime / TFLite)  │
    └────────┬─────────────────┘
             ↓
    ┌──────────────────────┐
    │ Decoder              │ (Beam search, sampling)
    │ (Multiple strategies)│
    └────────┬─────────────┘
             ↓
    ┌──────────────────────┐
    │ Post-processor       │ (De-tokenize, validate)
    └────────┬─────────────┘
             ↓
    ┌──────────────────────┐
    │ Cache & History      │ (Save result)
    └────────┬─────────────┘
             ↓
    Output to UI (Display, TTS, Export)
```

---

## 🧠 Core Engine NLP

### 1. Dukungan Bahasa

| Pair | Model | Lite | Balanced | Accurate | Domain |
|------|-------|------|----------|----------|--------|
| EN→ID | transformer-base | ✅ 45MB | ✅ 90MB | ✅ 180MB | General |
| ID→EN | - | ✅ | ✅ | ✅ | General |
| AR→ID | - | ✅ 50MB | ✅ 100MB | ✅ 150MB | General + Religious |
| ID→AR | - | ✅ | ✅ | ✅ | General |
| EN→ZH (S) | - | ✅ 48MB | ✅ 95MB | ✅ 185MB | General |
| EN→ZH (T) | - | ✅ | ✅ | ✅ | General |
| EN→Pinyin | - | ✅ 20MB | ✅ 40MB | ✅ 60MB | Romanization |
| ZH→EN | - | ✅ | ✅ | ✅ | General |

**Total size (all models)**: ~500MB → compressed & quantized to ~200MB

### 2. Core Inference Architecture

```cpp
// NLP Engine Core (C++ ONNX Runtime / TFLite)
class NLPEngine {
public:
  // Initialize with model path
  Status Initialize(const std::string& model_path);
  
  // Synchronous inference
  Status Infer(
    const TranslationRequest& request,
    TranslationResult& result
  );
  
  // Asynchronous streaming inference
  void InferAsync(
    const TranslationRequest& request,
    StreamCallback callback
  );
  
  // Batch inference (multiple sentences)
  Status InferBatch(
    const std::vector<TranslationRequest>& requests,
    std::vector<TranslationResult>& results
  );
  
  // Cache management
  void ClearCache();
  CacheStats GetCacheStats() const;

private:
  std::unique_ptr<ort::Session> ort_session_;
  std::unique_ptr<TFLiteInterpreter> tflite_interpreter_;
  std::shared_ptr<LRUCache<std::string, TranslationResult>> result_cache_;
  std::shared_ptr<MemoryPool> memory_pool_;
  std::shared_ptr<ThreadPool> thread_pool_;
};
```

### 3. Model Optimization

#### a) Quantization Strategy
- **INT8**: 4x compression, 10-15% accuracy loss (lite model)
- **FP16**: 2x compression, <2% accuracy loss (balanced model)
- **FP32**: No compression, baseline accuracy (accurate model)

#### b) Compression Techniques
- **Knowledge Distillation**: Teacher model → Student model
- **Pruning**: Remove 30-40% non-critical weights
- **LoRA Adaptation**: ~5% additional trainable parameters per language pair
- **Model Merging**: Consolidate encoder/decoder for inference optimization

#### c) Inference Optimization
- **Batching**: Group sentences for parallel processing
- **Streaming**: Start outputting tokens before full decode
- **Cache KV**: Cache key-value tensors for auto-regressive decoding
- **Operator Fusion**: Merge conv+bn+activation operations
- **Graph Optimization**: ONNX Graph Optimizer for reordering

### 4. Decoding Strategies (Production)

```kotlin
// Decoder Configuration
data class DecoderConfig(
  // Sampling
  val temperature: Float = 1.0f,        // [0.5, 2.0], < 1 = more deterministic
  val topK: Int = 40,                   // Nucleus sampling: keep top-K
  val topP: Float = 0.9f,               // Cumulative probability threshold
  val repetitionPenalty: Float = 1.2f,  // Discourage repetition
  
  // Beam search
  val beamWidth: Int = 4,               // Trade-off: quality vs speed
  val lengthPenalty: Float = 0.6f,      // Favor longer outputs
  val earlyStop: Boolean = true,        // Stop when best complete
  
  // Transliteration
  val enableTransliteration: Boolean = false,
  val transliterationMode: String = "ipa", // "ipa", "pinyin", "arabizi"
)
```

### 5. Performance Targets

| Metric | Target | Notes |
|--------|--------|-------|
| **Latency (p95)** | < 500ms | Per sentence (Balanced model) |
| **Throughput** | 100+ sentences/sec | Batching 32 (GPU) |
| **Memory Usage** | < 500MB | Loaded models + cache |
| **Model Size (Total)** | 200-250MB | All languages, quantized |
| **Accuracy (BLEU)** | > 0.75 | General domain |
| **Cache Hit Ratio** | 40-60% | For frequent phrases |

---

## 🎨 Design System Modern

### 1. Design Philosophy

**Prinsip**:
- **Minimalis**: Setiap elemen punya tujuan jelas
- **Konsisten**: 1 component = 1 behavior across platforms
- **Responsif**: Adaptif desktop (1920px) ↔ mobile (360px)
- **Aksesbel**: WCAG 2.1 AA standard, screen reader support
- **Performa**: 60 FPS animations, < 16ms frame time

### 2. Color System (Design Tokens)

#### Semantic Colors
```kotlin
// Light Theme
Primary      #6C5CE7 (Vibrant Purple)
Secondary    #00B4D8 (Sky Blue)
Tertiary     #FFD60A (Accent Yellow)
Error        #E63946 (Alert Red)
Success      #06A77D (Accept Green)
Warning      #F77F00 (Warning Orange)

// Neutral
Background  #FFFFFF
Surface     #F8F9FA
OnSurface   #1F2937 (Dark Gray)
Outline     #D1D5DB (Light Gray)

// Dark Theme
Background  #0F172A
Surface     #1E293B
OnSurface   #F1F5F9
Primary     #A78BFA (Lighter Purple)
```

#### Elevation System
```
Level 0: No shadow (disabled state)
Level 1: shadow(0px 1px 3px rgba(0,0,0,0.12)) - Subtle
Level 2: shadow(0px 3px 6px rgba(0,0,0,0.16)) - Cards
Level 3: shadow(0px 6px 16px rgba(0,0,0,0.20)) - Elevated panels
Level 4: shadow(0px 10px 28px rgba(0,0,0,0.25)) - Dialogs
```

### 3. Typography Hierarchy

```
Headline Large   (32sp, w700) - App title
Headline Medium  (28sp, w600) - Screen title
Headline Small   (24sp, w600) - Section header
Title Large      (22sp, w500) - Card titles
Title Medium     (16sp, w500) - Subsections
Body Large       (16sp, w400) - Main text
Body Medium      (14sp, w400) - Supporting
Body Small       (12sp, w400) - Captions
Label Large      (14sp, w500) - Buttons
Label Medium     (12sp, w500) - Tags, pills
Label Small      (11sp, w500) - Tiny labels
```

### 4. Layout Grid & Spacing

```
Grid System: 8px base unit
Spacing scale: 4, 8, 12, 16, 20, 24, 32, 40, 48

Container padding:
- Mobile: 16px
- Tablet: 20px
- Desktop: 24px

Column layout:
- Mobile: 1 column (full width - 2×16px)
- Tablet: 2 columns (50% width each)
- Desktop: 3 columns (33% width each) or 2 columns (sidebar layout)
```

### 5. Component Library

#### Reusable Components (Jetpack Compose + Qt/QML)

**Basic Components**:
- `Button` (Filled, Outlined, Text, Elevated)
- `TextField` (SingleLine, MultiLine, with validation)
- `Checkbox` / `RadioButton`
- `Switch`
- `Dropdown` (DropdownMenu variant)
- `Card` (Elevated, Outlined, Filled)
- `Divider`
- `Chip` (Input, Filter, Action, Suggestion)

**Complex Components**:
- `TranslateCard` (Source + Target + Swap button)
- `LanguagePicker` (With search & favorites)
- `ModelSelector` (Lite/Balanced/Accurate with info)
- `HistoryItem` (Swipeable, bookmarkable)
- `DictionaryCard` (With pronunciation, examples)
- `LearningModeCard` (Highlight, synonyms, context)
- `BatchProgressIndicator` (Linear + circular)
- `FloatingActionButton` (Quick translate, clipboard)

#### Android (Jetpack Compose)

```kotlin
// Example: TranslateCard.kt
@Composable
fun TranslateCard(
  sourceText: String,
  targetText: String,
  sourceLang: String,
  targetLang: String,
  onSourceChange: (String) -> Unit,
  onSwapLanguage: () -> Unit,
  onCopy: (String) -> Unit,
  onShare: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(16.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.elevatedCardElevation(
      defaultElevation = 4.dp
    )
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Source section
      TextField(
        value = sourceText,
        onValueChange = onSourceChange,
        label = { Text(sourceLang) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
      )
      
      // Swap button
      IconButton(onClick = onSwapLanguage) {
        Icon(Icons.Default.SwapCalls, "Swap languages")
      }
      
      // Target section
      SelectionContainer {
        Text(
          text = targetText,
          style = MaterialTheme.typography.bodyLarge,
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        )
      }
      
      // Action buttons
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = { onCopy(targetText) },
          modifier = Modifier.weight(1f)
        ) {
          Icon(Icons.Default.ContentCopy, "Copy")
          Text("Copy")
        }
        
        Button(
          onClick = { onShare(targetText) },
          modifier = Modifier.weight(1f)
        ) {
          Icon(Icons.Default.Share, "Share")
          Text("Share")
        }
      }
    }
  }
}
```

#### Desktop (Qt/QML)

```qml
// TranslateScreen.qml
import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

ApplicationWindow {
  width: 1000
  height: 700
  title: "x-translate"
  
  ColumnLayout {
    anchors.fill: parent
    anchors.margins: 16
    spacing: 16
    
    // Header
    Rectangle {
      color: "#6C5CE7"
      height: 60
      Layout.fillMaxWidth: true
      
      Text {
        text: "Translate"
        color: "white"
        font.pixelSize: 28
        font.weight: Font.Bold
        anchors.centerIn: parent
      }
    }
    
    // Main content
    RowLayout {
      Layout.fillWidth: true
      Layout.fillHeight: true
      spacing: 16
      
      // Source panel
      Rectangle {
        Layout.fillWidth: true
        border.color: "#D1D5DB"
        border.width: 1
        radius: 8
        
        ColumnLayout {
          anchors.fill: parent
          anchors.margins: 12
          spacing: 8
          
          Text {
            text: "English"
            font.pixelSize: 14
            font.bold: true
          }
          
          TextArea {
            Layout.fillWidth: true
            Layout.fillHeight: true
            placeholderText: "Enter text to translate"
          }
        }
      }
      
      // Action buttons
      ColumnLayout {
        spacing: 8
        
        Button {
          text: "⇄ Swap"
          onClicked: swapLanguages()
        }
        
        Button {
          text: "Translate"
          onClicked: performTranslation()
        }
      }
      
      // Target panel
      Rectangle {
        Layout.fillWidth: true
        border.color: "#D1D5DB"
        border.width: 1
        radius: 8
        
        ColumnLayout {
          anchors.fill: parent
          anchors.margins: 12
          spacing: 8
          
          Text {
            text: "Indonesian"
            font.pixelSize: 14
            font.bold: true
          }
          
          TextArea {
            Layout.fillWidth: true
            Layout.fillHeight: true
            readOnly: true
            text: translationResult
          }
        }
      }
    }
  }
}
```

### 6. Navigation & User Flow

#### Android (Bottom Navigation)
```
Bottom Nav Bar (Fixed, 4-5 items):
├── [Translate icon] Translate (Home)
├── [History icon] History
├── [Dictionary icon] Dictionary
├── [Book icon] Learning
└── [Settings icon] Settings
```

#### Desktop (Sidebar Navigation)
```
Left Sidebar (Collapsible):
├── Logo + App name
├── [Home icon] Translate
├── [History icon] History
├── [Dictionary icon] Dictionary
├── [Book icon] Learning
├── [Settings icon] Settings
└── [About icon] About
```

### 7. Micro-interactions & Animations

```kotlin
// Smooth transitions
TransitionSpec {
  durationMillis = 300
  easing = EaseInOutCubic
}

// Touch feedback
rippleEffect: Ripple(bounded = true, radius = 24.dp)

// Loading states
CircularProgressIndicator(modifier = Modifier.size(24.dp))

// Haptic feedback
HapticFeedback.Perform(HapticFeedbackType.LongPress)

// Keyboard animations
ime.animationSpec = tween(300, easing = EaseOut)
```

### 8. Accessibility (WCAG 2.1 AA)

✅ **Screen Reader Support**:
- Semantic labels for all interactive elements
- Content descriptions for icons
- Proper heading hierarchy
- Form label associations

✅ **Color Contrast**:
- Minimum 4.5:1 for body text
- 3:1 for UI components
- No color as only means of communication

✅ **Text Scaling**:
- Support 200% zoom
- Responsive font sizes (sp units)
- No horizontal scroll at 200%

✅ **Keyboard Navigation**:
- Tab order logical
- Enter/Space activates buttons
- Escape closes modals
- Keyboard shortcuts (Ctrl+C copy, Ctrl+V paste, etc)

✅ **Motion & Animation**:
- Respect `prefers-reduced-motion`
- No auto-playing animations
- Duration < 5 seconds

---

## ✨ Fitur Lengkap Produksi

### 1. Core Translation Features

#### a) Multi-Language Support
- [x] English ↔ Indonesian
- [x] Arabic ↔ Indonesian
- [x] English ↔ Mandarin (Simplified)
- [x] English ↔ Mandarin (Traditional)
- [x] English → Mandarin with Pinyin romanization
- [x] Auto-detect source language (using lightweight model)

#### b) Multiple Translation Styles
- **Formal** (bisnis, akademik, resmi)
- **Casual** (percakapan santai, SMS)
- **Literal** (terjemahan kata per kata)
- **Contextual** (mempertimbangkan konteks kalimat)
- **Business** (terminologi bisnis)
- **Academic** (istilah akademik)
- **Technical** (IT, engineering, medical)

**Implementation**: Separate LoRA adapters per style + decoder config

#### c) Transliteration
- **Arabic ↔ Latin**: Buckwalter, ISO 233, ALA-LC standards
- **Mandarin → Pinyin**: Hanyu Pinyin with tone marks
- **Mandarin → Jyutping**: Cantonese romanization

### 2. Advanced Translation Features

#### a) Streaming Translation (Real-time)
- Start displaying translation as it's being decoded
- Useful for chat, live subtitle
- Low latency < 100ms per token

#### b) Batch & Document Translation
- **Batch**: Translate multiple sentences simultaneously
- **Documents**: 
  - .txt (plain text)
  - .pdf (with layout preservation)
  - .docx (with formatting)
- Progress indicator + resume capability
- Exported with original formatting

#### c) Context-Aware Translation
- Dictionary lookup for current selection
- Previous context (last 2-3 sentences)
- Domain-specific glossary attachment
- Format preservation (capitalization, punctuation)

### 3. Supporting Features

#### a) Language Detection
- Automatic source language detection
- Confidence score display
- Manual override option
- Multi-script detection (Latin, Arabic, CJK)

#### b) Text-to-Speech (Offline)
- Android: Use built-in TTS engine (Indonesian, English, Chinese)
- Desktop: Festival/eSpeak alternative
- Playback speed control (0.5x - 2x)
- Voice selection
- Audio export

#### c) Speech-to-Text (Optional Offline)
- Android: Wav2Vec2 or Whisper-tiny
- Desktop: SpeechRecognition library
- Real-time transcription
- Confidence indication

#### d) OCR (Optical Character Recognition)
- Desktop: PaddleOCR or Tesseract 5
- Mobile: TensorFlow Lite OCR model (optional)
- Multi-language support (Latin, Arabic, CJK)
- Camera input + image file
- Perspective correction
- Text detection bounding boxes

#### e) Clipboard Monitor & Quick Translate Popup
- Monitor clipboard for new text
- Show floating popup with translation
- Auto-copy translated result
- Dismiss/close popup
- Settings toggle on/off
- Hotkey trigger (Ctrl+Shift+T on desktop)

### 4. History & Bookmark System

#### a) Smart History
- Timestamp + source language + target language
- Search full-text (source + target)
- Filter by language pair, date range, style
- Auto-clear old entries (configurable, default 30 days)
- Sync across devices (future: cloud optional)
- Export history as CSV/JSON
- Tags/labels for organization

#### b) Bookmark/Favorites
- Bookmark frequently used translations
- Custom folder organization
- Quick access for common phrases
- Export bookmarks

### 5. Learning Mode

#### a) Highlight & Synonyms
- Hover/tap word in target → show synonyms
- Click to replace with synonym
- Show confidence of synonym

#### b) Example Sentences
- Show similar sentences from translation memory
- Context usage examples
- Link to mini dictionary

#### c) Mini Dictionary (Offline)
- Quick lookup: English, Indonesian, Arabic, Chinese
- Word pronunciation
- Multiple meanings
- Example phrases
- Etymology (if available)
- Built-in database (SQLite)

### 6. Performance & Optimization

#### a) Model Variants
- **Lite**: ~45-50MB, 100-200ms latency, suitable for real-time
- **Balanced**: ~90-100MB, 200-400ms latency, recommended
- **Accurate**: ~180-200MB, 500-800ms latency, best quality

#### b) Incremental Model Updates
- Check for new models online (optional)
- Download delta updates only
- Automatic or manual triggering
- Rollback to previous version
- Version management UI

#### c) Advanced Performance Settings
- Cache size configuration (10-500MB)
- Thread count (auto-detect from CPU cores)
- Batch size for inference
- Memory limit enforcement
- Low-power mode (reduce model precision on-the-fly)
- Network timeout settings

### 7. Multi-Model Switching

- Runtime model selection (lite ↔ balanced ↔ accurate)
- Smooth transition without restart
- Memory-efficient unloading
- Profile-based presets (quick-reply, quality, balanced)

### 8. Settings & Preferences

- Dark/Light/Dynamic theme selection
- Font size adjustment
- History retention period
- Auto-copy setting
- TTS voice selection
- Clipboard monitor toggle
- Model selection
- Performance settings (cache, threads)
- Update preferences
- Language preferences

### 9. Export & Share

- Copy single translation
- Share via email/messaging
- Export as formatted document (PDF, DOCX)
- Export history
- Print (desktop)

### 10. Offline Full Support

✅ **Zero internet required**:
- Models: Pre-downloaded
- Dictionary: Bundled SQLite DB
- TTS/STT: Offline engines
- Updates: Optional, can use older models
- No telemetry/analytics (or local only)

---

## 🔧 Implementasi Teknis

### 1. Android Implementation

#### Build System: Gradle Modular

```gradle
// android/build.gradle.kts (Root)
plugins {
  id("com.android.application") version "8.0.0" apply false
  id("com.android.library") version "8.0.0" apply false
  id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

// android/app/build.gradle.kts
plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

android {
  compileSdk = 34
  
  defaultConfig {
    applicationId = "com.xtranslate"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0.0"
    
    ndk {
      abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86_64")
    }
    
    // Split APK per ABI
    splits {
      abi {
        isEnable = true
        reset()
        include("armeabi-v7a", "arm64-v8a", "x86_64")
        isUniversalApk = true  // also create universal APK
      }
    }
  }
  
  buildFeatures {
    compose = true
  }
  
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.0"
  }
  
  buildTypes {
    debug {
      isDebuggable = true
      isMinifyEnabled = false
    }
    release {
      isDebuggable = false
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  // Jetpack Compose
  implementation(platform("androidx.compose:compose-bom:2023.10.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.material3:material3:1.1.0")
  implementation("androidx.compose.foundation:foundation")
  
  // MVVM Architecture
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
  implementation("androidx.room:room-runtime:2.5.2")
  kapt("androidx.room:room-compiler:2.5.2")
  
  // Dependency Injection
  implementation("com.google.dagger:hilt-android:2.46")
  kapt("com.google.dagger:hilt-compiler:2.46")
  
  // ONNX Runtime
  implementation("com.microsoft.onnxruntime:onnxruntime-android:1.15.1")
  
  // TFLite
  implementation("org.tensorflow:tensorflow-lite:2.12.0")
  implementation("org.tensorflow:tensorflow-lite-gpu:2.12.0")
  
  // Serialization
  implementation("com.google.code.gson:gson:2.10.1")
  implementation("com.squareup.okhttp3:okhttp:4.11.0")
  
  // Logging
  implementation("com.jakewharton.timber:timber:5.0.1")
  
  // Testing
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.mockito:mockito-core:5.2.0")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```

#### Core JNI Integration

```kotlin
// android/core/src/main/kotlin/com/xtranslate/core/jni/NativeTranslator.kt
package com.xtranslate.core.jni

class NativeTranslator {
  companion object {
    init {
      System.loadLibrary("x-translate-core")
    }
  }
  
  // Native method declarations
  external fun initializeModel(modelPath: String): Boolean
  external fun translate(sourceText: String): String
  external fun translateBatch(sourceTexts: Array<String>): Array<String>
  external fun setDecoderConfig(configJson: String): Boolean
  external fun clearCache(): Unit
  external fun getMemoryUsage(): Long
  external fun shutdown(): Unit
}
```

```cpp
// android/core/src/main/cpp/jni_wrapper.cpp
#include <jni.h>
#include "translator.h"

static std::unique_ptr<xlate::NLPEngine> g_engine;

JNIEXPORT jboolean JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_initializeModel(
    JNIEnv* env, jclass, jstring model_path) {
  const char* path = env->GetStringUTFChars(model_path, nullptr);
  bool success = g_engine->Initialize(path);
  env->ReleaseStringUTFChars(model_path, path);
  return success;
}

JNIEXPORT jstring JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_translate(
    JNIEnv* env, jclass, jstring source_text) {
  const char* text = env->GetStringUTFChars(source_text, nullptr);
  xlate::TranslationRequest req;
  req.source_text = text;
  xlate::TranslationResult res;
  
  xlate::Status status = g_engine->Infer(req, res);
  env->ReleaseStringUTFChars(source_text, text);
  
  if (!status.ok()) {
    return env->NewStringUTF("");
  }
  return env->NewStringUTF(res.target_text.c_str());
}
```

#### CMakeLists for Native Build

```cmake
# android/CMakeLists.txt
cmake_minimum_required(VERSION 3.22)
project(x-translate-core)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# ONNX Runtime
find_package(ONNX REQUIRED)

# Source files
set(SOURCES
  android/core/src/main/cpp/src/translator.cpp
  android/core/src/main/cpp/src/tokenizer.cpp
  android/core/src/main/cpp/src/model_manager.cpp
  android/core/src/main/cpp/src/jni_wrapper.cpp
  android/core/src/main/cpp/src/logger.cpp
  android/core/src/main/cpp/src/memory_pool.cpp
)

# Create shared library
add_library(x-translate-core SHARED ${SOURCES})

# Link ONNX Runtime
target_link_libraries(x-translate-core PRIVATE
  ONNX::ONNX
  log
  android
)

target_include_directories(x-translate-core PRIVATE
  android/core/src/main/cpp/include
)
```

### 2. Desktop Implementation (C++ Qt/QML)

#### CMake Configuration

```cmake
# desktop/CMakeLists.txt
cmake_minimum_required(VERSION 3.22)
project(x-translate-desktop)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

# Find required packages
find_package(Qt6 REQUIRED COMPONENTS
  Core
  Gui
  Qml
  Quick
  Sql
  Network
)

# ONNX Runtime, TFLite, OpenCV for OCR
find_package(ONNX REQUIRED)
find_package(TensorflowLite REQUIRED)
find_package(OpenCV REQUIRED)

# Source files
set(SOURCES
  src/main.cpp
  src/app.cpp
  src/ui/main_window.cpp
  src/core/nlp_engine.cpp
  src/core/onnx_translator.cpp
  src/core/model_manager.cpp
  src/service/clipboard_monitor.cpp
  src/service/batch_translator.cpp
  src/util/logger.cpp
)

# Resources (QML, images, etc.)
set(RESOURCES
  resources.qrc
)

# Create executable
add_executable(x-translate-desktop ${SOURCES} ${RESOURCES})

# Link libraries
target_link_libraries(x-translate-desktop PRIVATE
  Qt6::Core
  Qt6::Gui
  Qt6::Qml
  Qt6::Quick
  Qt6::Sql
  ONNX::ONNX
  TensorflowLite::TensorflowLite
  opencv_core
  opencv_imgproc
)

# Platform-specific settings
if(WIN32)
  set_target_properties(x-translate-desktop PROPERTIES
    WIN32_EXECUTABLE ON
  )
endif()
```

#### CMake Presets (Multi-config)

```json
{
  "version": 3,
  "configurePresets": [
    {
      "name": "default",
      "displayName": "Default (Unix Makefiles)",
      "generator": "Unix Makefiles",
      "binaryDir": "${sourceDir}/build",
      "cacheVariables": {
        "CMAKE_BUILD_TYPE": "Release",
        "CMAKE_PREFIX_PATH": "/usr/local/Qt6"
      }
    },
    {
      "name": "debug",
      "displayName": "Debug",
      "generator": "Unix Makefiles",
      "binaryDir": "${sourceDir}/build-debug",
      "cacheVariables": {
        "CMAKE_BUILD_TYPE": "Debug"
      }
    },
    {
      "name": "msvc",
      "displayName": "MSVC (Windows)",
      "generator": "Visual Studio 17 2022",
      "binaryDir": "${sourceDir}/build-msvc",
      "cacheVariables": {
        "CMAKE_BUILD_TYPE": "Release"
      },
      "condition": {
        "type": "equals",
        "lhs": "${hostSystemName}",
        "rhs": "Windows"
      }
    }
  ],
  "buildPresets": [
    {
      "name": "default",
      "configurePreset": "default"
    },
    {
      "name": "debug",
      "configurePreset": "debug"
    },
    {
      "name": "release",
      "configurePreset": "default",
      "configuration": "Release"
    }
  ]
}
```

### 3. Thread Safety & Memory Management

#### Android (Kotlin Coroutines)

```kotlin
// Core translation with thread safety
class TranslationRepository(
  private val nativeTranslator: NativeTranslator,
  private val cache: TranslationCache
) {
  suspend fun translate(request: TranslationRequest): TranslationResult =
    withContext(Dispatchers.Default) {
      // Check cache first
      cache.get(request.hashCode())?.let { return@withContext it }
      
      // Perform translation
      val result = nativeTranslator.translate(request.sourceText)
      
      // Cache result
      cache.put(request.hashCode(), result)
      
      result
    }
  
  // Batch translation with proper resource cleanup
  fun translateBatch(
    requests: List<TranslationRequest>,
    onProgress: (Int) -> Unit
  ): Flow<TranslationResult> = flow {
    requests.forEachIndexed { index, request ->
      val result = translate(request)
      emit(result)
      onProgress(index + 1)
    }
  }
}
```

#### Desktop (C++ Thread Pool)

```cpp
// desktop/src/util/thread_pool.h
class ThreadPool {
public:
  ThreadPool(size_t num_threads = std::thread::hardware_concurrency());
  ~ThreadPool();
  
  template <typename F, typename... Args>
  auto EnqueueTask(F&& f, Args&&... args) {
    using return_type = decltype(f(args...));
    
    auto task = std::make_shared<std::packaged_task<return_type()>>(
      std::bind(std::forward<F>(f), std::forward<Args>(args)...)
    );
    
    std::future<return_type> res = task->get_future();
    
    {
      std::unique_lock<std::mutex> lock(queue_mutex_);
      tasks_.emplace([task]() { (*task)(); });
    }
    
    condition_.notify_one();
    return res;
  }

private:
  std::vector<std::thread> workers_;
  std::queue<std::function<void()>> tasks_;
  std::mutex queue_mutex_;
  std::condition_variable condition_;
  bool stop_ = false;
};
```

### 4. Memory Optimization

#### Model Loading & Caching

```cpp
// desktop/src/core/model_manager.h
class ModelManager {
public:
  Status LoadModel(const std::string& model_path, ModelVariant variant);
  void UnloadModel(ModelVariant variant);
  
  const OnnxModel* GetModel(ModelVariant variant) const {
    auto it = loaded_models_.find(variant);
    if (it != loaded_models_.end()) {
      return it->second.get();
    }
    return nullptr;
  }
  
  size_t GetMemoryUsage() const {
    size_t total = 0;
    for (const auto& [variant, model] : loaded_models_) {
      total += model->GetMemorySize();
    }
    return total;
  }

private:
  std::unordered_map<ModelVariant, std::unique_ptr<OnnxModel>> loaded_models_;
  static constexpr size_t MAX_MEMORY_MB = 500;
};
```

#### LRU Cache Implementation

```cpp
// desktop/src/util/lru_cache.h
template <typename Key, typename Value>
class LRUCache {
public:
  explicit LRUCache(size_t capacity) : capacity_(capacity) {}
  
  bool Get(const Key& key, Value& value) {
    auto it = cache_map_.find(key);
    if (it == cache_map_.end()) {
      return false;
    }
    
    // Move to front (most recently used)
    cache_list_.splice(cache_list_.begin(), cache_list_, it->second);
    value = it->second->second;
    return true;
  }
  
  void Put(const Key& key, const Value& value) {
    auto it = cache_map_.find(key);
    
    if (it != cache_map_.end()) {
      // Update existing
      cache_list_.splice(cache_list_.begin(), cache_list_, it->second);
      it->second->second = value;
      return;
    }
    
    // Add new
    cache_list_.emplace_front(key, value);
    cache_map_[key] = cache_list_.begin();
    
    // Evict if over capacity
    if (cache_map_.size() > capacity_) {
      cache_map_.erase(cache_list_.back().first);
      cache_list_.pop_back();
    }
  }

private:
  using CacheList = std::list<std::pair<Key, Value>>;
  CacheList cache_list_;
  std::unordered_map<Key, typename CacheList::iterator> cache_map_;
  size_t capacity_;
};
```

### 5. Logging & Crash Handling

#### Android Logging

```kotlin
// android/app/src/main/kotlin/com/xtranslate/util/Logger.kt
object Logger {
  init {
    Timber.plant(if (BuildConfig.DEBUG) {
      Timber.DebugTree()
    } else {
      FileLoggingTree(context)
    })
  }
  
  fun d(tag: String, msg: String, tr: Throwable? = null) {
    Timber.tag(tag).d(tr, msg)
  }
  
  fun e(tag: String, msg: String, tr: Throwable) {
    Timber.tag(tag).e(tr, msg)
    // Send to crash handler
    CrashHandler.report(tag, msg, tr)
  }
}

// Offline crash handler
class CrashHandler(context: Context) : Thread.UncaughtExceptionHandler {
  override fun uncaughtException(t: Thread, e: Throwable) {
    // Write to file (offline)
    val crashLog = CrashLog(
      timestamp = System.currentTimeMillis(),
      threadName = t.name,
      exception = e.stackTraceToString(),
      androidVersion = Build.VERSION.SDK_INT,
      appVersion = BuildConfig.VERSION_NAME
    )
    
    File(context.cacheDir, "crash_${System.currentTimeMillis()}.log").writeText(
      Json.encodeToString(crashLog)
    )
  }
}
```

#### Desktop Logging

```cpp
// desktop/src/util/logger.h
class Logger {
public:
  static void Initialize(const std::string& log_dir);
  
  static void Debug(const std::string& tag, const std::string& msg);
  static void Info(const std::string& tag, const std::string& msg);
  static void Warning(const std::string& tag, const std::string& msg);
  static void Error(const std::string& tag, const std::string& msg);
  
private:
  static std::ofstream log_file_;
  static std::mutex log_mutex_;
  static std::string FormatLog(Level level, const std::string& tag, const std::string& msg);
};
```

### 6. Data Management

#### Android (Room + SQLite)

```kotlin
// android/app/src/main/kotlin/com/xtranslate/data/local/TranslateDatabase.kt
@Database(
  entities = [HistoryEntity::class, BookmarkEntity::class],
  version = 1
)
abstract class TranslateDatabase : RoomDatabase() {
  abstract fun historyDao(): HistoryDao
  abstract fun bookmarkDao(): BookmarkDao
  
  companion object {
    @Volatile
    private var instance: TranslateDatabase? = null
    
    fun getInstance(context: Context): TranslateDatabase =
      instance ?: synchronized(this) {
        Room.databaseBuilder(
          context,
          TranslateDatabase::class.java,
          "translate.db"
        )
          .enableMultiInstanceInvalidation()
          .build()
          .also { instance = it }
      }
  }
}

@Entity(tableName = "history")
data class HistoryEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val sourceText: String,
  val targetText: String,
  val sourceLang: String,
  val targetLang: String,
  val style: String,
  val timestamp: Long = System.currentTimeMillis(),
  val tags: String = ""  // JSON array
)

@Dao
interface HistoryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entity: HistoryEntity)
  
  @Query("SELECT * FROM history ORDER BY timestamp DESC LIMIT :limit")
  fun getRecent(limit: Int = 50): Flow<List<HistoryEntity>>
  
  @Query("SELECT * FROM history WHERE sourceText LIKE :query OR targetText LIKE :query")
  suspend fun search(query: String): List<HistoryEntity>
  
  @Query("DELETE FROM history WHERE timestamp < :before")
  suspend fun deleteOlderThan(before: Long)
}
```

#### Desktop (SQLite + File-based)

```cpp
// desktop/src/data/database.h
class Database {
public:
  bool Initialize(const std::string& db_path);
  
  // History operations
  bool AddHistory(const TranslationRecord& record);
  std::vector<TranslationRecord> GetHistory(size_t limit = 100);
  std::vector<TranslationRecord> SearchHistory(const std::string& query);
  bool DeleteHistory(long timestamp_before);
  
  // Bookmark operations
  bool AddBookmark(const TranslationRecord& record);
  std::vector<TranslationRecord> GetBookmarks();
  bool RemoveBookmark(long id);
  
  // Settings
  bool SaveSetting(const std::string& key, const std::string& value);
  std::string GetSetting(const std::string& key, const std::string& default_value);

private:
  sqlite3* db_ = nullptr;
  std::mutex db_mutex_;
};
```

---

## 🧪 QA & Testing

### 1. Unit Tests

#### Android (Kotlin + JUnit + Mockito)

```kotlin
// android/app/src/test/kotlin/com/xtranslate/viewmodel/TranslateViewModelTest.kt
class TranslateViewModelTest {
  private lateinit var viewModel: TranslateViewModel
  private val mockRepository = mock<TranslationRepository>()
  
  @Before
  fun setup() {
    viewModel = TranslateViewModel(mockRepository)
  }
  
  @Test
  fun `translate emits result on success`() = runTest {
    val sourceText = "Hello"
    val expected = "Halo"
    
    `when`(mockRepository.translate(any())).thenReturn(
      TranslationResult("Hello", "Halo", "en", "id")
    )
    
    viewModel.translate(sourceText)
    
    val result = viewModel.translationResult.first()
    assertEquals(expected, result.targetText)
  }
  
  @Test
  fun `translate emits error on failure`() = runTest {
    `when`(mockRepository.translate(any())).thenThrow(
      RuntimeException("Network error")
    )
    
    viewModel.translate("Hello")
    
    val error = viewModel.error.first()
    assertNotNull(error)
  }
}
```

#### Desktop (Google Test + Mock)

```cpp
// desktop/test/unit/test_tokenizer.cpp
#include <gtest/gtest.h>
#include "tokenizer.h"

class TokenizerTest : public ::testing::Test {
protected:
  Tokenizer tokenizer;
  
  void SetUp() override {
    ASSERT_TRUE(tokenizer.LoadVocabulary("path/to/vocab.json"));
  }
};

TEST_F(TokenizerTest, TokenizeEnglish) {
  auto tokens = tokenizer.Tokenize("Hello world");
  EXPECT_EQ(tokens.size(), 3);  // [CLS] Hello world
  EXPECT_EQ(tokens[1], 18975);  // ID of "Hello"
}

TEST_F(TokenizerTest, TokenizeArabic) {
  auto tokens = tokenizer.Tokenize("السلام عليكم");
  EXPECT_GT(tokens.size(), 1);
}
```

### 2. Integration Tests

#### Android

```kotlin
// android/app/src/androidTest/kotlin/com/xtranslate/TranslationIntegrationTest.kt
@RunWith(AndroidJUnit4::class)
class TranslationIntegrationTest {
  @get:Rule
  val composeTestRule = createComposeRule()
  
  @Test
  fun translationFlow() = runTest {
    composeTestRule.setContent {
      TranslateScreen()
    }
    
    // Enter text
    composeTestRule.onNodeWithTag("source_input").performTextInput("Hello")
    
    // Tap translate
    composeTestRule.onNodeWithTag("translate_btn").performClick()
    
    // Verify result appears
    composeTestRule.onNodeWithText("Halo", substring = true).assertExists()
  }
}
```

#### Desktop

```cpp
// desktop/test/integration/test_translation_pipeline.cpp
TEST(TranslationPipelineTest, FullPipeline) {
  NLPEngine engine;
  ASSERT_TRUE(engine.Initialize("path/to/model.onnx"));
  
  TranslationRequest req{
    .source_text = "Hello, how are you?",
    .source_lang = "en",
    .target_lang = "id"
  };
  
  TranslationResult res;
  auto status = engine.Infer(req, res);
  
  EXPECT_TRUE(status.ok());
  EXPECT_FALSE(res.target_text.empty());
  EXPECT_EQ(res.target_lang, "id");
}
```

### 3. Performance Testing

#### Latency Benchmark

```kotlin
// android/app/src/test/kotlin/com/xtranslate/LatencyBenchmark.kt
@BenchmarkRule
class LatencyBenchmark {
  private lateinit var nativeTranslator: NativeTranslator
  
  @Before
  fun setup() {
    nativeTranslator = NativeTranslator()
    nativeTranslator.initializeModel("path/to/model.onnx")
  }
  
  @Test
  fun benchmarkTranslateSingleSentence() {
    val sentence = "Hello, my name is John"
    
    val startTime = SystemClock.elapsedRealtimeNanos()
    nativeTranslator.translate(sentence)
    val endTime = SystemClock.elapsedRealtimeNanos()
    
    val latency = (endTime - startTime) / 1_000_000.0  // ms
    println("Latency: ${latency}ms")
    
    assertTrue(latency < 500)  // Should be < 500ms
  }
  
  @Test
  fun benchmarkBatchTranslation() {
    val sentences = List(32) { "Sentence number $it" }.toTypedArray()
    
    val startTime = SystemClock.elapsedRealtimeNanos()
    nativeTranslator.translateBatch(sentences)
    val endTime = SystemClock.elapsedRealtimeNanos()
    
    val totalLatency = (endTime - startTime) / 1_000_000.0
    val avgLatency = totalLatency / sentences.size
    
    println("Batch latency: ${avgLatency}ms per sentence")
    assertTrue(avgLatency < 400)
  }
}
```

### 4. Quality Metrics (BLEU, CER)

```python
# shared/scripts/evaluate_quality.py
import sacrebleu
import editdistance

def calculate_bleu(predictions, references):
    bleu = sacrebleu.corpus_bleu(predictions, [references])
    return bleu.score

def calculate_cer(predictions, references):
    """Character Error Rate"""
    errors = 0
    total = 0
    
    for pred, ref in zip(predictions, references):
        dist = editdistance.eval(pred, ref)
        errors += dist
        total += len(ref)
    
    return (errors / total) * 100 if total > 0 else 0

# Load test data
test_data = load_json("test-data.json")

predictions = [model.translate(item["source"]) for item in test_data]
references = [item["target"] for item in test_data]

bleu_score = calculate_bleu(predictions, [references])
cer_score = calculate_cer(predictions, references)

print(f"BLEU Score: {bleu_score:.4f}")
print(f"CER Score: {cer_score:.2f}%")
```

### 5. Regression Testing (Multilingual)

```yaml
# .github/workflows/regression-test.yml
name: Multilingual Regression Test

on: [push, pull_request]

jobs:
  regression-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Download test data
        run: bash shared/scripts/download-test-data.sh
      
      - name: Run translation tests
        run: |
          for lang_pair in en-id ar-id en-zh-s en-zh-t; do
            python shared/scripts/evaluate_quality.py $lang_pair
          done
      
      - name: Check quality thresholds
        run: |
          python shared/scripts/check_thresholds.py
          # BLEU > 0.75 for general domain
          # CER < 10% for speech
```

---

## 🔄 CI/CD Pipeline

### 1. GitHub Actions Workflows

#### Android Build

```yaml
# .github/workflows/android-build.yml
name: Android Build & Test

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      
      - name: Setup Android SDK
        uses: android-actions/setup-android@v2
        with:
          api-level: 34
          ndk-version: 25.1.8937393
      
      - name: Build Android project
        run: |
          cd android
          ./gradlew clean build
      
      - name: Run unit tests
        run: |
          cd android
          ./gradlew test
      
      - name: Run instrumented tests
        uses: reactivecircus/android-emulator-runner@v2
        with:
          api-level: 29
          script: cd android && ./gradlew connectedAndroidTest
      
      - name: Build release APK
        run: |
          cd android
          ./gradlew assembleRelease
      
      - name: Upload APK artifacts
        uses: actions/upload-artifact@v3
        with:
          name: android-apk
          path: android/app/build/outputs/apk/**/*.apk
      
      - name: Build AAB (Bundle)
        run: |
          cd android
          ./gradlew bundleRelease
      
      - name: Upload AAB artifacts
        uses: actions/upload-artifact@v3
        with:
          name: android-aab
          path: android/app/build/outputs/bundle/**/*.aab
```

#### Desktop Build (Windows & Linux)

```yaml
# .github/workflows/desktop-build.yml
name: Desktop Build & Test

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  build-linux:
    runs-on: ubuntu-22.04
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Install dependencies
        run: |
          sudo apt-get update
          sudo apt-get install -y \
            build-essential cmake ninja-build \
            qt6-base-dev qt6-qml-dev \
            libonnxruntime-dev libtensorflow-lite-dev \
            libopencv-dev
      
      - name: Build for Linux
        run: |
          cd desktop
          bash build-scripts/build.sh
      
      - name: Run unit tests
        run: |
          cd desktop/build
          ctest --output-on-failure
      
      - name: Create AppImage
        run: |
          cd desktop
          bash build-scripts/create-appimage.sh
      
      - name: Create .deb package
        run: |
          # dpkg packaging
          cd desktop
          mkdir -p build-deb/DEBIAN
          mkdir -p build-deb/usr/bin
          mkdir -p build-deb/usr/share/applications
          cp build/x-translate-desktop build-deb/usr/bin/
          cp installer/linux/x-translate.desktop build-deb/usr/share/applications/
          dpkg-deb --build build-deb x-translate.deb
      
      - name: Upload Linux artifacts
        uses: actions/upload-artifact@v3
        with:
          name: desktop-linux
          path: |
            desktop/*.AppImage
            desktop/*.deb
  
  build-windows:
    runs-on: windows-latest
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Install vcpkg
        uses: lukka/run-vcpkg@v10
        with:
          vcpkgDirectory: ${{ github.workspace }}/vcpkg
          vcpkgGitCommitId: 'master'
      
      - name: Install Qt6
        uses: jurplel/install-qt-action@v3
        with:
          version: '6.5.0'
          host: 'windows'
          target: 'desktop'
      
      - name: Build for Windows (MSVC)
        run: |
          cd desktop
          bash build-scripts/build-win.bat
      
      - name: Create NSIS Installer
        run: |
          cd desktop
          choco install nsis
          makensis installer/nsis/installer.nsi
      
      - name: Create portable ZIP
        run: |
          cd desktop/build/Release
          7z a ../../x-translate-portable.zip x-translate-desktop.exe *.dll
      
      - name: Upload Windows artifacts
        uses: actions/upload-artifact@v3
        with:
          name: desktop-windows
          path: |
            desktop/*.exe
            desktop/*.zip
```

### 2. Code Quality Checks

```yaml
# .github/workflows/quality-checks.yml
name: Code Quality

on: [push, pull_request]

jobs:
  lint-android:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '17'
      - name: Run ktlint
        run: |
          cd android
          ./gradlew ktlintCheck
      - name: Run Android lint
        run: |
          cd android
          ./gradlew lint
  
  lint-cpp:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Install clang-format
        run: sudo apt-get install -y clang-format
      - name: Check C++ formatting
        run: |
          find desktop/src -name "*.cpp" -o -name "*.h" | xargs clang-format -i
          git diff --exit-code
      - name: Run cppcheck
        run: |
          sudo apt-get install -y cppcheck
          cppcheck desktop/src --error-exitcode=1
  
  dependency-check:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Check for vulnerable dependencies
        uses: dependabot/dependabot-action@main
```

---

## 📦 Build & Release

### 1. Android Release Process

#### Sign APK/AAB

```bash
# android/build-scripts/sign-apk.sh
#!/bin/bash

APK_PATH=$1
OUTPUT_PATH=$2
KEYSTORE_PATH=$3
KEYSTORE_PASSWORD=$4
KEY_ALIAS=$5
KEY_PASSWORD=$6

# Sign APK
jarsigner -verbose \
  -sigalg SHA256withRSA \
  -digestalg SHA-256 \
  -keystore "$KEYSTORE_PATH" \
  -storepass "$KEYSTORE_PASSWORD" \
  -keypass "$KEY_PASSWORD" \
  "$APK_PATH" \
  "$KEY_ALIAS"

# Verify signature
jarsigner -verify -verbose -certs "$APK_PATH"

# Optimize with zipalign
zipalign -v 4 "$APK_PATH" "$OUTPUT_PATH"

echo "APK signed and aligned: $OUTPUT_PATH"
```

#### Build Release Variants

```bash
# android/build-scripts/build-release.sh
#!/bin/bash

cd android

# Build release APK with size optimization
./gradlew clean assembleRelease \
  -Pandroid.enableR8=true \
  -Pandroid.enableResourceOptimizations=true \
  -Pandroid.useAndroidX=true

# Split APK per ABI (smaller downloads)
./gradlew bundleRelease

# Output locations:
# APK: app/build/outputs/apk/release/
# AAB: app/build/outputs/bundle/release/
# ABI splits: app/build/outputs/apk/release/splits/

echo "✅ Release builds completed"
echo "APK size: $(du -h app/build/outputs/apk/release/*.apk)"
echo "AAB size: $(du -h app/build/outputs/bundle/release/*.aab)"
```

### 2. Desktop Release Process

#### Windows Release (MSVC + NSIS Installer)

```batch
@echo off
REM desktop/build-scripts/build-release.bat

setlocal enabledelayedexpansion

REM Setup environment
call "C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Auxiliary\Build\vcvars64.bat"

REM Build with CMake
cd desktop
mkdir build-release
cd build-release

cmake -G "Visual Studio 17 2022" ^
  -DCMAKE_BUILD_TYPE=Release ^
  -DCMAKE_PREFIX_PATH="C:\Qt\6.5.0\msvc2019_64" ^
  ..

cmake --build . --config Release --parallel 4

REM Create NSIS installer
cd ..
"C:\Program Files (x86)\NSIS\makensis.exe" installer/nsis/installer.nsi

echo Build complete!
dir /s /b build-release\Release\x-translate-desktop.exe
dir /s /b installer\*.exe
```

#### Linux Release (AppImage + .deb)

```bash
# desktop/build-scripts/build-release.sh
#!/bin/bash

set -e

cd desktop

# Build
mkdir -p build-release
cd build-release

cmake -G Ninja \
  -DCMAKE_BUILD_TYPE=Release \
  -DCMAKE_PREFIX_PATH=/usr/lib/cmake/Qt6 \
  ..

cmake --build . --parallel $(nproc)

cd ..

# Create AppImage
bash build-scripts/create-appimage.sh

# Create .deb package
mkdir -p deb-package/DEBIAN
mkdir -p deb-package/usr/bin
mkdir -p deb-package/usr/share/applications

cat > deb-package/DEBIAN/control << EOF
Package: x-translate
Version: 1.0.0
Architecture: amd64
Maintainer: x-translate team <team@xtranslate.com>
Description: Offline multilingual translator
 Fast, accurate, fully offline translation
 Supports English, Indonesian, Arabic, Chinese
EOF

cp build-release/x-translate-desktop deb-package/usr/bin/
cp installer/linux/x-translate.desktop deb-package/usr/share/applications/

dpkg-deb --build deb-package x-translate.deb

echo "✅ Builds complete:"
echo "AppImage: $(ls -lh x-translate*.AppImage)"
echo "DEB: $(ls -lh x-translate.deb)"
```

### 3. Automated Release Workflow

```yaml
# .github/workflows/release.yml
name: Release

on:
  push:
    tags:
      - 'v*'

jobs:
  release:
    runs-on: ubuntu-latest
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Get version from tag
        id: tag_version
        run: echo "VERSION=${GITHUB_REF#refs/tags/v}" >> $GITHUB_OUTPUT
      
      - name: Download build artifacts
        uses: actions/download-artifact@v3
      
      - name: Create Release Notes
        run: |
          python shared/scripts/generate-changelog.py \
            --version ${{ steps.tag_version.outputs.VERSION }} \
            > RELEASE_NOTES.md
      
      - name: Create GitHub Release
        uses: softprops/action-gh-release@v1
        with:
          body_path: RELEASE_NOTES.md
          files: |
            android-apk/**/*.apk
            android-aab/**/*.aab
            desktop-windows/**/*.exe
            desktop-windows/**/*.zip
            desktop-linux/**/*.AppImage
            desktop-linux/**/*.deb
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
      
      - name: Update version file
        run: |
          echo "${{ steps.tag_version.outputs.VERSION }}" > VERSION
          git add VERSION
          git commit -m "chore: bump version to ${{ steps.tag_version.outputs.VERSION }}"
          git push
```

---

## 🗺️ Roadmap Rinci

### Phase 1: Foundation (Months 1-3) ✅

#### Core Architecture & Infrastructure
- [x] Setup repository struktur (android/, desktop/, shared/)
- [x] Design system tokens & components
- [x] ONNX Runtime integration (Android JNI + Desktop native)
- [x] Database schema (Room/SQLite history, bookmarks)
- [x] CI/CD pipeline setup (GitHub Actions)

#### Minimum Viable Features
- [x] Single translation (English ↔ Indonesian)
- [x] Offline model inference
- [x] History tracking
- [x] Basic UI (Jetpack Compose, Qt/QML)
- [x] Settings screen
- [x] Light/Dark theme

#### Quality Assurance
- [x] Unit tests (Core logic)
- [x] Integration tests (Pipeline)
- [x] Performance benchmarks
- [x] BLEU evaluation setup

### Phase 2: Feature Expansion (Months 4-6) ⏳

#### Additional Language Pairs
- [ ] Arabic ↔ Indonesian models (training & quantization)
- [ ] English ↔ Mandarin models
- [ ] Language detection model
- [ ] Multi-style decoding (formal, casual, etc)

#### Enhanced Features
- [ ] Batch translation
- [ ] Document translation (.txt, .pdf, .docx)
- [ ] Transliteration (Arabic, Mandarin Pinyin)
- [ ] Text-to-Speech offline
- [ ] Clipboard monitor & quick popup
- [ ] Bookmarks & smart history (search/filter/tagging)

#### UI/UX Refinement
- [ ] Responsive layout (tablet support)
- [ ] Gesture animations
- [ ] Accessibility features (WCAG 2.1)
- [ ] Keyboard shortcuts
- [ ] User feedback (tooltips, hints)

### Phase 3: Advanced Features (Months 7-9) ⏳

#### ML/NLP Enhancements
- [ ] Learning mode (highlight, synonyms, examples)
- [ ] Mini dictionary (offline, multi-language)
- [ ] Context-aware translation (domain glossary)
- [ ] Incremental model updates
- [ ] Multi-model switching (lite/balanced/accurate)

#### Offline Features
- [ ] OCR offline (desktop)
- [ ] Speech-to-Text offline (optional)
- [ ] Handwriting recognition (optional)
- [ ] QR code reader (optional)

#### Performance Optimization
- [ ] Model quantization INT8/FP16
- [ ] Knowledge distillation
- [ ] Operator fusion
- [ ] Streaming inference
- [ ] LRU cache optimization

### Phase 4: Polish & Release (Months 10-12) ⬜

#### Quality Assurance
- [ ] Regression testing (multilingual)
- [ ] Performance testing (latency, memory, size)
- [ ] Crash handling & offline logging
- [ ] Edge case handling
- [ ] User acceptance testing (UAT)

#### Release Preparation
- [ ] Signing & code obfuscation (Android)
- [ ] Windows NSIS installer
- [ ] Linux AppImage + .deb
- [ ] Store submission (Google Play, Windows Store)
- [ ] Release notes & documentation
- [ ] Launch marketing & announcements

#### Post-Release
- [ ] User feedback monitoring
- [ ] Bug fixes & patches
- [ ] Performance monitoring
- [ ] Feature roadmap refinement

---

## ✅ Checklist Progres

### Phase 1: Foundation

**Core Setup**:
- ⬜ Repository structure created
- ⬜ Build system configured (Gradle + CMake)
- ⬜ Design system tokens defined
- ⬜ CI/CD pipelines setup

**Android Development**:
- ⬜ Jetpack Compose UI setup
- ⬜ ONNX Runtime JNI wrapper
- ⬜ Room database integration
- ⬜ Basic translation screen
- ⬜ History & settings screens

**Desktop Development**:
- ⬜ Qt6/QML UI setup
- ⬜ ONNX Runtime integration
- ⬜ SQLite database
- ⬜ Main window & screens
- ⬜ CMake build configuration

**ML Models**:
- ⬜ English-Indonesian baseline model
- ⬜ Model quantization (INT8)
- ⬜ Model testing & evaluation
- ⬜ Asset packing & bundling

**Testing**:
- ⬜ Unit test suite (Android & Desktop)
- ⬜ Integration tests
- ⬜ Performance benchmarks
- ⬜ BLEU evaluation setup

### Phase 2: Expansion

**Language Pairs**:
- ⬜ Arabic-Indonesian model
- ⬜ English-Mandarin models
- ⬜ Language detection model
- ⬜ Model quantization & optimization

**Features**:
- ⬜ Batch translation
- ⬜ Document translation (txt, pdf, docx)
- ⬜ Transliteration
- ⬜ Text-to-Speech
- ⬜ Clipboard monitor
- ⬜ Smart history
- ⬜ Bookmarks

**UI/UX**:
- ⬜ Tablet responsiveness
- ⬜ Animation & micro-interactions
- ⬜ Accessibility (WCAG 2.1)
- ⬜ Keyboard shortcuts
- ⬜ Dark/Light/Dynamic theme

### Phase 3: Advanced

**ML Features**:
- ⬜ Learning mode
- ⬜ Mini dictionary
- ⬜ Context-aware translation
- ⬜ Incremental model updates
- ⬜ Multi-model switching

**Offline Features**:
- ⬜ OCR (Desktop)
- ⬜ Speech-to-Text
- ⬜ Handwriting recognition
- ⬜ QR code reader

**Performance**:
- ⬜ Model quantization (FP16/INT8)
- ⬜ Knowledge distillation
- ⬜ Operator fusion
- ⬜ Streaming inference
- ⬜ Cache optimization

### Phase 4: Release

**QA**:
- ⬜ Regression tests
- ⬜ Performance benchmarks
- ⬜ Crash handling
- ⬜ Edge case testing
- ⬜ UAT

**Build & Release**:
- ⬜ APK signing & optimization
- ⬜ AAB generation
- ⬜ Windows installer (NSIS)
- ⬜ Linux packaging (AppImage, .deb)
- ⬜ Release notes
- ⬜ Store submission

**Monitoring**:
- ⬜ Crash reporting
- ⬜ Performance metrics
- ⬜ User feedback
- ⬜ Bug tracking

---

## 📊 Metrik Performa

### Target Performance Metrics

| Metrik | Target | Lite Model | Balanced | Accurate |
|--------|--------|-----------|----------|----------|
| **Latency (p50)** | < 300ms | 80-120ms | 200-300ms | 400-600ms |
| **Latency (p95)** | < 500ms | 150-200ms | 350-450ms | 700-900ms |
| **Throughput (32 batch)** | 100+ sent/s | 150 | 75 | 40 |
| **Memory (idle)** | < 200MB | 120MB | 250MB | 400MB |
| **Memory (loaded model)** | < 500MB | 150MB | 350MB | 550MB |
| **Model Size (compressed)** | 45-200MB | 45MB | 95MB | 190MB |
| **BLEU Score (general)** | > 0.75 | 0.70 | 0.78 | 0.85 |
| **Cache Hit Ratio** | 40-60% | - | - | - |

### Monitoring Dashboard (CI/CD)

```yaml
# Track these metrics per release:
- Latency P50/P95 (per language pair, per model variant)
- Throughput (sentences/second)
- Memory footprint (MB)
- Model size (MB)
- BLEU score (multi-domain)
- Cache effectiveness (hit ratio)
- Crash rate (crashes/1000 sessions)
- User retention (day 1, day 7, day 30)
```

---

## ⚠️ Risk & Mitigation

### Technical Risks

| Risk | Severity | Mitigation |
|------|----------|-----------|
| Model accuracy below BLEU 0.75 | High | Early evaluation, use pretrained teacher models, fine-tune on domain data |
| Memory overflow on low-end devices | Medium | Lite model (45MB), aggressive quantization, streaming inference |
| JNI threading bugs | Medium | Thread pool with mutex, thorough testing, sanitizer checks |
| Model loading latency > 5s | Medium | Pre-load on app startup, cache in memory, lazy load on demand |
| Cross-platform UI inconsistency | Low | Shared design tokens, component testing, pixel-perfect screenshots |

### Operational Risks

| Risk | Severity | Mitigation |
|------|----------|-----------|
| Model file corruption (disk) | Medium | CRC32 checksum validation, automatic re-download |
| Offline crash without logs | Medium | Offline crash handler writing to disk, upload on next session |
| Model versioning conflicts | Low | Semantic versioning, migration scripts, backward compatibility |
| App store rejection (APK size) | Medium | Aggressive R8 optimization, split APK per ABI, compression |

### Market Risks

| Risk | Severity | Mitigation |
|------|----------|-----------|
| User acquisition slow | Medium | Focus on quality first, organic growth, App Store Optimization (ASO) |
| Competing products (Google Translate) | High | Focus on offline + privacy, niche languages, specific domains |
| Privacy concerns | Medium | No data collection, open-source code review, privacy policy clarity |

---

## 📝 Changelog Terstruktur

### Format Changelog

```markdown
## [Version] - YYYY-MM-DD

### Added
- New features introduced

### Changed
- Modifications to existing functionality

### Fixed
- Bug fixes and corrections

### Improved
- Performance improvements and optimizations

### Removed
- Deprecated or removed features

### Security
- Security updates

### Known Issues
- Known limitations or bugs
```

### Example Changelog Entry

```markdown
## [1.0.0] - 2026-04-24

### Added
- Initial public release
- English ↔ Indonesian translation
- Arabic ↔ Indonesian translation
- English ↔ Mandarin Chinese translation
- Offline mode with bundled models (Lite, Balanced, Accurate)
- History tracking and search
- Bookmarks functionality
- Text-to-Speech output
- Clipboard monitor with quick popup (Desktop)
- Batch translation support
- Document translation (.txt, .pdf, .docx)
- Dark/Light theme support
- Jetpack Compose UI (Android)
- Qt6/QML UI (Desktop)
- Android: Split APK per ABI (armeabi-v7a, arm64-v8a, x86_64)
- Windows: NSIS installer + portable ZIP
- Linux: AppImage + .deb package

### Improved
- Model quantization (INT8) for 4x compression
- LRU caching for 40-60% cache hit ratio
- Streaming inference for real-time output
- Multi-threaded batch processing
- Memory optimization (< 500MB peak usage)
- BLEU score 0.78 on general domain

### Fixed
- JNI thread safety issues
- Memory leaks in native code
- Crash handling for offline environments
- UI responsiveness on low-end devices

### Security
- APK code obfuscation (R8/ProGuard)
- Native code stack protection
- SQLite database encryption (optional)
- Secure model file validation (CRC32)

### Performance Benchmarks
- Latency (Balanced model): 200-400ms p95
- Throughput: 75 sentences/sec (batch 32)
- Model size: 95MB (Balanced, quantized)
- Memory: 250MB loaded (Balanced)
- App size: 65MB (Android APK), 150MB (Desktop portable)

### Known Issues
- Android: Manifest merge conflicts on some old devices
- Desktop: Qt on Windows 7 may show rendering glitches
- Limited to subset of technical terminology (v1)
```

---

## 📚 Dokumentasi Referensi

### Dokumen Lanjutan (untuk dibuat)

1. **ARCHITECTURE.md** - Detail lengkap clean architecture, module dependencies, data flow
2. **NLP_ENGINE.md** - ONNX Runtime setup, model loading, inference pipeline, optimization
3. **UI_DESIGN_SYSTEM.md** - Color palette, typography, components, responsive breakpoints
4. **API_REFERENCE.md** - Public API documentation (for plugins/extensions)
5. **DEPLOYMENT.md** - Step-by-step deployment guide untuk CI/CD, signing, store submission
6. **DEVELOPMENT.md** - Developer setup, build commands, debugging tips
7. **TROUBLESHOOTING.md** - Common issues, solutions, performance tuning
8. **CONTRIBUTION.md** - Coding standards, PR process, testing requirements

---

## 🎬 Getting Started

### Quick Setup (Android)

```bash
cd android
./gradlew assembleDebug
# APK di: app/build/outputs/apk/debug/

# Atau dengan Android Studio: File > Open > android/
```

### Quick Setup (Desktop)

```bash
cd desktop
cmake --preset default
cmake --build build --config Release

# Output: build/x-translate-desktop
```

### Download Models

```bash
bash shared/scripts/download-onnx-models.py \
  --language-pairs en-id ar-id en-zh \
  --variants lite balanced accurate \
  --output-dir resources/models/
```

---

## 📞 Support & Feedback

- **Issues & Bugs**: GitHub Issues
- **Feature Requests**: GitHub Discussions
- **Documentation**: See `/docs` folder
- **Contributing**: See CONTRIBUTING.md

---

**Status**: This blueprint is comprehensive and production-ready. All phases should follow this architecture strictly for consistency and quality.

**Last Updated**: April 2026
**Version**: 1.0
**Owner**: x-translate Team
