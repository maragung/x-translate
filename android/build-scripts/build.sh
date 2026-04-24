#!/bin/bash
# Build script for x-translate Android project

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

echo "🔨 Building x-translate Android project..."
echo "Project root: $PROJECT_ROOT"

cd "$PROJECT_ROOT"

# Build debug APK
echo "📦 Building debug APK..."
./gradlew assembleDebug

echo "✅ Debug build complete!"
echo "APK location: app/build/outputs/apk/debug/"

# Display APK info
APK_PATH=$(find app/build/outputs/apk/debug -name "*.apk" -type f | head -1)
if [ -f "$APK_PATH" ]; then
  APK_SIZE=$(du -h "$APK_PATH" | cut -f1)
  echo "APK size: $APK_SIZE"
  echo "APK path: $APK_PATH"
fi
