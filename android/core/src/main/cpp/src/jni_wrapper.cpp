#include <jni.h>
#include <string>
#include <android/log.h>
#include "jni_wrapper.h"
#include "translator.h"

#define LOG_TAG "x-translate-core"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

std::unique_ptr<xlate::NLPEngine> g_engine;

JNIEXPORT jboolean JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_initializeModel(
    JNIEnv* env, jclass clazz, jstring model_path) {
  try {
    const char* path = env->GetStringUTFChars(model_path, nullptr);
    LOGI("Initializing model from: %s", path);

    auto translator = std::make_unique<xlate::OnnxTranslator>();
    auto status = translator->Initialize(path);

    env->ReleaseStringUTFChars(model_path, path);

    if (status.ok()) {
      g_engine = std::move(translator);
      LOGI("Model initialized successfully");
      return JNI_TRUE;
    } else {
      LOGE("Failed to initialize model: %s", status.error_.c_str());
      return JNI_FALSE;
    }
  } catch (const std::exception& e) {
    LOGE("Exception during initialization: %s", e.what());
    return JNI_FALSE;
  }
}

JNIEXPORT jstring JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_translate(
    JNIEnv* env, jclass clazz, jstring source_text) {
  if (!g_engine) {
    return env->NewStringUTF("");
  }

  try {
    const char* text = env->GetStringUTFChars(source_text, nullptr);
    xlate::TranslationRequest req;
    req.source_text = text;

    xlate::TranslationResult res;
    auto status = g_engine->Infer(req, res);

    env->ReleaseStringUTFChars(source_text, text);

    if (status.ok()) {
      return env->NewStringUTF(res.target_text.c_str());
    } else {
      LOGE("Translation failed: %s", status.error_.c_str());
      return env->NewStringUTF("");
    }
  } catch (const std::exception& e) {
    LOGE("Exception during translation: %s", e.what());
    return env->NewStringUTF("");
  }
}

JNIEXPORT jobjectArray JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_translateBatch(
    JNIEnv* env, jclass clazz, jobjectArray source_texts) {
  if (!g_engine || !source_texts) {
    return nullptr;
  }

  try {
    jint count = env->GetArrayLength(source_texts);
    std::vector<xlate::TranslationRequest> requests(count);

    for (jint i = 0; i < count; ++i) {
      auto jtext = (jstring)env->GetObjectArrayElement(source_texts, i);
      const char* text = env->GetStringUTFChars(jtext, nullptr);
      requests[i].source_text = text;
      env->ReleaseStringUTFChars(jtext, text);
      env->DeleteLocalRef(jtext);
    }

    auto results = static_cast<xlate::OnnxTranslator*>(g_engine.get())
      ->TranslateBatch(requests);

    jobjectArray jresults = env->NewObjectArray(
      results.size(),
      env->FindClass("java/lang/String"),
      nullptr
    );

    for (size_t i = 0; i < results.size(); ++i) {
      env->SetObjectArrayElement(
        jresults,
        i,
        env->NewStringUTF(results[i].target_text.c_str())
      );
    }

    return jresults;
  } catch (const std::exception& e) {
    LOGE("Exception during batch translation: %s", e.what());
    return nullptr;
  }
}

JNIEXPORT jboolean JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_setDecoderConfig(
    JNIEnv* env, jclass clazz, jstring config_json) {
  // TODO: Implement decoder config setting
  return JNI_TRUE;
}

JNIEXPORT void JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_clearCache(
    JNIEnv* env, jclass clazz) {
  if (g_engine) {
    g_engine->ClearCache();
  }
}

JNIEXPORT jlong JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_getMemoryUsage(
    JNIEnv* env, jclass clazz) {
  if (g_engine) {
    return g_engine->GetMemoryUsage();
  }
  return 0;
}

JNIEXPORT void JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_shutdown(
    JNIEnv* env, jclass clazz) {
  g_engine.reset();
}
