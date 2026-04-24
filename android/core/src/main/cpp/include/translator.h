#include <jni.h>
#include <string>
#include <vector>
#include <memory>
#include "nlp_engine.h"

#ifndef X_TRANSLATE_TRANSLATOR_H
#define X_TRANSLATE_TRANSLATOR_H

namespace xlate {

class OnnxTranslator {
public:
  OnnxTranslator();
  ~OnnxTranslator();

  Status Initialize(const std::string& model_path);
  Status Translate(const TranslationRequest& request, TranslationResult& result);
  std::vector<TranslationResult> TranslateBatch(
    const std::vector<TranslationRequest>& requests
  );

  void ClearCache();
  long GetMemoryUsage() const;

private:
  class Impl;
  std::unique_ptr<Impl> impl_;
};

}  // namespace xlate

#endif  // X_TRANSLATE_TRANSLATOR_H
