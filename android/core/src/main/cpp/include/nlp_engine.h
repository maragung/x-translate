#include <jni.h>
#include <string>

#ifndef X_TRANSLATE_NATIVE_H
#define X_TRANSLATE_NATIVE_H

namespace xlate {

// Status handling
struct Status {
  bool ok() const { return error_.empty(); }
  std::string error_;
};

// Translation request/response
struct TranslationRequest {
  std::string source_text;
  std::string source_lang = "en";
  std::string target_lang = "id";
  std::string model_variant = "balanced";
  float temperature = 1.0f;
  int top_k = 40;
  float top_p = 0.9f;
};

struct TranslationResult {
  std::string target_text;
  float confidence = 1.0f;
  long latency_ms = 0;
};

// Main NLP Engine interface
class NLPEngine {
public:
  virtual ~NLPEngine() = default;

  // Initialize with model path
  virtual Status Initialize(const std::string& model_path) = 0;

  // Synchronous inference
  virtual Status Infer(
    const TranslationRequest& request,
    TranslationResult& result
  ) = 0;

  // Cleanup
  virtual void Shutdown() = 0;

  // Get memory usage in bytes
  virtual long GetMemoryUsage() const = 0;

  // Clear cache
  virtual void ClearCache() = 0;
};

// Create instance
NLPEngine* CreateNLPEngine();

}  // namespace xlate

#endif  // X_TRANSLATE_NATIVE_H
