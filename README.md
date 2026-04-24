# x-translate

**Offline Multilingual Translator for Android & Desktop**

Fast, accurate, privacy-first translation powered by ONNX Runtime and TensorFlow Lite.

## 🚀 Quick Start

### Android
```bash
cd android
cp local.properties.template local.properties
# Edit local.properties with your SDK/NDK paths
./gradlew assembleDebug
```

### Desktop (Coming Soon)
```bash
cd desktop
cmake --preset default
cmake --build build --config Release
```

## 📋 Documentation

- **[plan.md](plan.md)** - Complete blueprint with architecture, features, roadmap
- **[ANDROID_IMPLEMENTATION.md](ANDROID_IMPLEMENTATION.md)** - Android MVP implementation details

## ✨ Features

### Current (MVP - Android)
- ✅ English ↔ Indonesian translation
- ✅ Fully offline, no internet required
- ✅ ONNX Runtime inference
- ✅ Modern UI (Jetpack Compose)
- ✅ Split APK per ABI
- ✅ Material3 design system

### Planned (Phase 2-4)
- 🔄 Additional languages (Arabic, Chinese)
- 🔄 Batch & document translation
- 🔄 Text-to-Speech
- 🔄 Clipboard monitor
- 🔄 Learning mode with examples
- 🔄 OCR offline
- 🔄 Desktop version (Qt/QML)

## 🏗️ Project Structure

```
x-translate/
├── android/                 # Android platform
│   ├── app/                # Main app module
│   ├── core/               # JNI + native code
│   ├── build-logic/        # Build conventions
│   └── build-scripts/      # Automation
├── desktop/                # Desktop platform (WIP)
├── docs/                   # Documentation
├── shared/                 # Shared resources
├── plan.md                 # Complete blueprint
├── ANDROID_IMPLEMENTATION.md
└── README.md
```

## 📊 Performance Targets

| Metric | Target |
|--------|--------|
| Latency (p95) | < 500ms |
| Memory (loaded) | < 500MB |
| Model size (quantized) | 95MB |
| APK size | < 80MB |
| BLEU score | > 0.75 |

## 🏛️ Architecture

- **Clean Architecture**: Presentation → Domain → Data → Core
- **MVVM Pattern**: Jetpack Compose + ViewModel
- **Dependency Injection**: Hilt
- **Database**: Room (Android), SQLite (Desktop)
- **ML Inference**: ONNX Runtime + TensorFlow Lite
- **Design System**: Material3 with custom tokens

## 🛠️ Tech Stack

### Android
- Kotlin 1.9.22
- Jetpack Compose 2024.02
- Room Database 2.6
- Hilt 2.50
- ONNX Runtime 1.15
- TensorFlow Lite 2.12

### Desktop
- C++ 17 (Qt6/QML)
- CMake 3.22+
- ONNX Runtime
- TensorFlow Lite

## 📝 License

[Add your license here]

## 👥 Contributing

See CONTRIBUTING.md

---

**Status**: MVP Implementation in Progress | **Last Updated**: April 2026
