#include <jni.h>
#include <memory>
#include <string>
#include "nlp_engine.h"

#ifndef X_TRANSLATE_JNI_H
#define X_TRANSLATE_JNI_H

#ifdef __cplusplus
extern "C" {
#endif

// Native translator instance (global)
static std::unique_ptr<xlate::NLPEngine> g_engine;

JNIEXPORT jboolean JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_initializeModel(
    JNIEnv* env, jclass clazz, jstring model_path
);

JNIEXPORT jstring JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_translate(
    JNIEnv* env, jclass clazz, jstring source_text
);

JNIEXPORT jobjectArray JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_translateBatch(
    JNIEnv* env, jclass clazz, jobjectArray source_texts
);

JNIEXPORT jboolean JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_setDecoderConfig(
    JNIEnv* env, jclass clazz, jstring config_json
);

JNIEXPORT void JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_clearCache(
    JNIEnv* env, jclass clazz
);

JNIEXPORT jlong JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_getMemoryUsage(
    JNIEnv* env, jclass clazz
);

JNIEXPORT void JNICALL
Java_com_xtranslate_core_jni_NativeTranslator_shutdown(
    JNIEnv* env, jclass clazz
);

#ifdef __cplusplus
}
#endif

#endif  // X_TRANSLATE_JNI_H
