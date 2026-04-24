#!/bin/bash
# Release build script for x-translate Android project

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

echo "🔨 Building x-translate Android release..."
echo "Project root: $PROJECT_ROOT"

cd "$PROJECT_ROOT"

# Clean
echo "🧹 Cleaning..."
./gradlew clean

# Build release APKs (split per ABI)
echo "📦 Building release APKs..."
./gradlew assembleRelease

# Build AAB (Android App Bundle)
echo "📦 Building AAB..."
./gradlew bundleRelease

echo "✅ Release build complete!"
echo ""
echo "📊 Build artifacts:"
echo "  APKs: app/build/outputs/apk/release/"
echo "  AAB:  app/build/outputs/bundle/release/"

# Show sizes
echo ""
echo "📈 Artifact sizes:"
find app/build/outputs/apk/release -name "*.apk" -type f | while read apk; do
  SIZE=$(du -h "$apk" | cut -f1)
  BASENAME=$(basename "$apk")
  echo "  $BASENAME: $SIZE"
done

AAB=$(find app/build/outputs/bundle/release -name "*.aab" -type f | head -1)
if [ -f "$AAB" ]; then
  SIZE=$(du -h "$AAB" | cut -f1)
  echo "  $(basename $AAB): $SIZE"
fi
