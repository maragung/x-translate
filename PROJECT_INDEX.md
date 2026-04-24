# x-translate Project Index

**Complete file manifest for x-translate Android MVP implementation**

## Root Level Files

```
/
├── README.md                      - Project overview & quick start
├── plan.md                        - 3000+ line comprehensive blueprint
├── STATUS.md                      - Implementation completion report
├── ANDROID_IMPLEMENTATION.md      - Android MVP setup & features
├── IMPLEMENTATION_SUMMARY.md      - Detailed implementation details
├── PROJECT_INDEX.md               - This file (manifest of all components)
├── setup-scripts.sh              - Utility scripts
└── .git/                         - Version control
```

## Android Module Structure

### Root Configuration

```
android/
├── build.gradle.kts              - Root Gradle build script
├── settings.gradle.kts           - Module configuration
├── CMakeLists.txt                - Native C++ build configuration
├── gradle.properties             - Build properties
├── local.properties.template    - SDK/NDK paths template
├── .gitignore                    - Git ignore rules
└── README.md                     - Android module guide
```

### Gradle & Dependencies

```
android/gradle/
├── libs.versions.toml            - Centralized version catalog (30+ libs)
└── wrapper/                      - Gradle wrapper (future)
```

### Build Automation

```
android/build-scripts/
├── build.sh                      - Debug APK automation
├── build-release.sh             - Release APK + AAB automation
```

### Build Logic Module

```
android/build-logic/
├── build.gradle.kts
└── src/main/kotlin/com/xtranslate/buildlogic/
    └── AndroidConventionPlugin.kt - Build convention plugins
        ├── AndroidAppConventionPlugin
        ├── AndroidLibraryConventionPlugin
        └── KotlinConventionPlugin
```

### App Module (Main Application)

```
android/app/
├── build.gradle.kts              - App-specific Gradle config
├── proguard-rules.pro            - R8 obfuscation rules
├── AndroidManifest.xml           - App manifest
│
└── src/main/
    ├── kotlin/com/xtranslate/
    │   ├── App.kt
    │   │   └── Application entry point
    │   │   └── Hilt initialization
    │   │   └── Timber logging setup
    │   │
    │   ├── MainActivity.kt
    │   │   └── Activity entry point
    │   │   └── Jetpack Compose setup
    │   │   └── Material3 theme application
    │   │
    │   ├── ui/
    │   │   ├── screens/
    │   │   │   └── TranslateScreen.kt
    │   │   │       ├── Source/Target panels
    │   │   │       ├── Swap languages button
    │   │   │       ├── Copy/Share actions
    │   │   │       ├── Loading indicator
    │   │   │       └── Error display
    │   │   │
    │   │   └── theme/
    │   │       ├── Theme.kt
    │   │       │   ├── Light color scheme
    │   │       │   ├── Dark color scheme
    │   │       │   └── Material3 theme composable
    │   │       │
    │   │       └── Type.kt
    │   │           ├── Headlines (32sp - 24sp)
    │   │           ├── Titles (22sp - 14sp)
    │   │           ├── Body (16sp - 12sp)
    │   │           └── Labels (14sp - 11sp)
    │   │
    │   ├── data/
    │   │   ├── model/
    │   │   │   └── Entities.kt
    │   │   │       ├── HistoryEntity
    │   │   │       ├── BookmarkEntity
    │   │   │       └── TranslationRecord
    │   │   │
    │   │   └── repository/
    │   │       └── TranslationRepository.kt
    │   │           ├── translate()
    │   │           └── translateBatch()
    │   │
    │   ├── viewmodel/
    │   │   └── TranslateViewModel.kt
    │   │       ├── TranslateUiState
    │   │       ├── updateSourceText()
    │   │       ├── translate()
    │   │       ├── swapLanguages()
    │   │       ├── copyToClipboard()
    │   │       └── share()
    │   │
    │   └── util/
    │       └── ClipboardHelper.kt
    │           ├── copyToClipboard()
    │           └── getFromClipboard()
    │
    └── res/
        ├── values/
        │   ├── strings.xml              - 19 string resources
        │   ├── colors.xml               - Color definitions (18 colors)
        │   ├── dimens.xml              - Dimensions & spacing
        │   └── styles.xml              - Theme styles (light/dark)
        │
        └── [drawable/mipmap/raw to be added]
```

### Core Module (Native Code)

```
android/core/
├── build.gradle.kts              - Native library Gradle config
├── CMakeLists.txt                - CMake configuration
├── consumer-rules.pro            - ProGuard rules for library
│
└── src/main/
    ├── kotlin/com/xtranslate/core/
    │   │
    │   ├── jni/
    │   │   └── NativeTranslator.kt
    │   │       ├── initializeModel()
    │   │       ├── translate()
    │   │       ├── translateBatch()
    │   │       ├── setDecoderConfig()
    │   │       ├── clearCache()
    │   │       ├── getMemoryUsage()
    │   │       └── shutdown()
    │   │
    │   └── nlp/
    │       └── Models.kt
    │           ├── TranslationRequest
    │           ├── TranslationResult
    │           ├── DecoderConfig
    │           ├── ModelVariant enum
    │           │   ├── LITE (45MB)
    │           │   ├── BALANCED (95MB)
    │           │   └── ACCURATE (190MB)
    │           └── TranslationStyle enum
    │               ├── FORMAL
    │               ├── CASUAL
    │               ├── LITERAL
    │               ├── CONTEXTUAL
    │               ├── BUSINESS
    │               ├── ACADEMIC
    │               └── TECHNICAL
    │
    └── cpp/
        ├── CMakeLists.txt        - C++ build configuration
        │
        ├── include/
        │   ├── nlp_engine.h
        │   │   ├── Status struct
        │   │   ├── TranslationRequest struct
        │   │   ├── TranslationResult struct
        │   │   ├── NLPEngine abstract class
        │   │   │   ├── Initialize()
        │   │   │   ├── Infer()
        │   │   │   ├── Shutdown()
        │   │   │   ├── GetMemoryUsage()
        │   │   │   └── ClearCache()
        │   │   └── CreateNLPEngine() factory
        │   │
        │   ├── translator.h
        │   │   ├── OnnxTranslator class
        │   │   ├── Initialize()
        │   │   ├── Translate()
        │   │   ├── TranslateBatch()
        │   │   ├── ClearCache()
        │   │   ├── GetMemoryUsage()
        │   │   └── Pimpl pattern (Impl class)
        │   │
        │   └── jni_wrapper.h
        │       ├── Global engine instance
        │       └── 7 JNI function declarations
        │
        └── src/
            ├── jni_wrapper.cpp
            │   ├── initializeModel JNI
            │   ├── translate JNI
            │   ├── translateBatch JNI
            │   ├── setDecoderConfig JNI
            │   ├── clearCache JNI
            │   ├── getMemoryUsage JNI
            │   ├── shutdown JNI
            │   ├── Error handling
            │   └── Android logging
            │
            └── translator.cpp
                ├── OnnxTranslator implementation
                ├── ONNX Environment setup
                ├── Session creation
                ├── Graph optimization
                ├── Thread pool config
                ├── Batch processing
                └── Memory management
```

## Architecture Overview

```
┌─────────────────────────────────────────────────┐
│         PRESENTATION LAYER (Jetpack Compose)    │
│  ┌───────────────────────────────────────────┐  │
│  │ TranslateScreen                           │  │
│  │ - Source/Target panels                    │  │
│  │ - Swap, Copy, Share buttons               │  │
│  │ - Loading & error states                  │  │
│  └───────────────────────────────────────────┘  │
└───────────────┬─────────────────────────────────┘
                │
┌───────────────▼─────────────────────────────────┐
│  SERVICE LAYER & VIEW MODELS                    │
│  ┌───────────────────────────────────────────┐  │
│  │ TranslateViewModel                        │  │
│  │ - State management (TranslateUiState)     │  │
│  │ - MVVM pattern                            │  │
│  │ - Coroutine-based async                   │  │
│  └───────────────────────────────────────────┘  │
└───────────────┬─────────────────────────────────┘
                │
┌───────────────▼─────────────────────────────────┐
│  REPOSITORY LAYER                               │
│  ┌───────────────────────────────────────────┐  │
│  │ TranslationRepository                     │  │
│  │ - Data access abstraction                 │  │
│  │ - Single/batch translation                │  │
│  │ - Native translator delegation            │  │
│  └───────────────────────────────────────────┘  │
└───────────────┬─────────────────────────────────┘
                │
┌───────────────▼─────────────────────────────────┐
│  JNI WRAPPER & NATIVE BRIDGE                    │
│  ┌───────────────────────────────────────────┐  │
│  │ NativeTranslator (Kotlin JNI)             │  │
│  │ jni_wrapper.cpp (JNI bridge)              │  │
│  └───────────────────────────────────────────┘  │
└───────────────┬─────────────────────────────────┘
                │
┌───────────────▼─────────────────────────────────┐
│  CORE INFERENCE ENGINE (C++ Native)             │
│  ┌───────────────────────────────────────────┐  │
│  │ OnnxTranslator                            │  │
│  │ - ONNX Runtime initialization             │  │
│  │ - Model inference                         │  │
│  │ - Batch processing                        │  │
│  │ - Memory management                       │  │
│  └───────────────────────────────────────────┘  │
└─────────────────────────────────────────────────┘
```

## Key Files by Category

### Kotlin Source Files (11)
1. App.kt
2. MainActivity.kt
3. TranslateScreen.kt
4. TranslateViewModel.kt
5. Theme.kt
6. Type.kt
7. Models.kt
8. Entities.kt
9. TranslationRepository.kt
10. NativeTranslator.kt
11. ClipboardHelper.kt

### C++ Source Files (5)
1. translator.cpp
2. jni_wrapper.cpp
3. nlp_engine.h
4. translator.h
5. jni_wrapper.h

### Build Configuration (13)
1. build.gradle.kts (root)
2. settings.gradle.kts
3. gradle.properties
4. gradle/libs.versions.toml
5. app/build.gradle.kts
6. core/build.gradle.kts
7. build-logic/build.gradle.kts
8. CMakeLists.txt (root)
9. core/CMakeLists.txt (via compiler)
10. proguard-rules.pro
11. consumer-rules.pro
12. local.properties.template
13. AndroidManifest.xml

### Resources (4)
1. strings.xml
2. colors.xml
3. dimens.xml
4. styles.xml

### Scripts (2)
1. build.sh
2. build-release.sh

### Documentation (6)
1. README.md (root)
2. STATUS.md
3. plan.md
4. ANDROID_IMPLEMENTATION.md
5. IMPLEMENTATION_SUMMARY.md
6. PROJECT_INDEX.md (this file)

### Configuration (4)
1. .gitignore
2. AndroidConventionPlugin.kt
3. android/README.md
4. setup-scripts.sh

**Total: 55+ files**

---

## Build Variants

### ABI Splits (Architecture)
- armeabi-v7a (32-bit ARM)
- arm64-v8a (64-bit ARM) - primary
- x86_64 (Intel 64-bit emulator)

### Build Types
- debug (debuggable, unminified)
- release (production, obfuscated with R8)

**Total Combinations**: 6 variants

---

## Dependencies Included (30+)

### UI Framework
- androidx.compose:compose-bom 2024.02.00
- androidx.compose.material3
- androidx.compose.foundation
- androidx.activity:activity-compose 1.8.1

### Architecture
- androidx.lifecycle:lifecycle-viewmodel-compose
- androidx.lifecycle:lifecycle-runtime-ktx 2.7.0

### Database
- androidx.room:room-runtime 2.6.1
- androidx.room:room-ktx

### Dependency Injection
- com.google.dagger:hilt-android 2.50

### ML Inference
- com.microsoft.onnxruntime:onnxruntime-android 1.15.1
- org.tensorflow:tensorflow-lite 2.12.0
- org.tensorflow:tensorflow-lite-gpu

### Utilities
- com.jakewharton.timber:timber 5.0.1
- com.squareup.okhttp3:okhttp 4.11.0
- com.google.code.gson:gson 2.10.1
- org.jetbrains.kotlinx:kotlinx-coroutines-android 1.7.3
- androidx.core:core-ktx 1.12.0

### Testing
- junit 4.13.2
- androidx.test.espresso:espresso-core 3.5.1
- org.mockito:mockito-core 5.2.0
- mockito-kotlin 5.1.0

---

## Usage Quick Reference

### Build Commands
```bash
# Debug APK
./gradlew assembleDebug
bash build-scripts/build.sh

# Release APK + AAB
bash build-scripts/build-release.sh

# Run tests
./gradlew test

# Clean build
./gradlew clean
```

### Project Navigation
- **UI Code**: `app/src/main/kotlin/com/xtranslate/ui/`
- **Business Logic**: `app/src/main/kotlin/com/xtranslate/viewmodel/`
- **Data Access**: `app/src/main/kotlin/com/xtranslate/data/`
- **Native Bridge**: `core/src/main/kotlin/com/xtranslate/core/jni/`
- **C++ Core**: `core/src/main/cpp/src/`
- **Design System**: `app/src/main/kotlin/com/xtranslate/ui/theme/`
- **Resources**: `app/src/main/res/values/`

---

## Next Implementation Steps

### Phase 2 (High Priority)
1. Download ONNX model for EN-ID
2. Implement Room DAO interfaces
3. Complete translator.cpp inference
4. Add unit tests

### Phase 3 (Medium Priority)
1. Additional screens (History, Settings, Dictionary)
2. Service implementations (TTS, language detection)
3. Clipboard monitor
4. Additional languages

### Phase 4 (Low Priority)
1. Advanced features
2. Desktop version
3. Performance tuning
4. Extended testing

---

**Generated**: April 24, 2026
**Version**: 1.0-MVP
**Status**: Complete & Ready for Phase 2
