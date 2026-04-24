# x-translate Android Module

Mobile application untuk Android (Kotlin + C++ JNI).

## Struktur

- **app/** - Modul aplikasi utama (Jetpack Compose UI)
- **core/** - Modul core (Native C++ inference, ONNX Runtime)
- **build-logic/** - Build conventions dan plugins
- **build-scripts/** - Automated build scripts

## Setup

```bash
# Copy template ke local.properties
cp local.properties.template local.properties

# Edit local.properties dengan path SDK/NDK Anda
nano local.properties

# Build debug
./gradlew assembleDebug

# Build release
bash build-scripts/build-release.sh
```

## Fitur (MVP)

- ✅ English ↔ Indonesian translation
- ✅ Offline inference (ONNX Runtime)
- ✅ History tracking (Room DB)
- ✅ Modern UI (Jetpack Compose)
- ✅ Split APK per ABI
- ⏳ Text-to-Speech
- ⏳ Clipboard monitor
- ⏳ Multi-language support

## Dependencies

- ONNX Runtime v1.15.1
- TensorFlow Lite v2.12.0
- Jetpack Compose Material3
- Room Database
- Hilt Dependency Injection
- Timber Logging

## Performance Targets

- Latency (p95): < 500ms
- Model size: 95MB (quantized)
- APK size: < 80MB
- Memory: < 500MB
