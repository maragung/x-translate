plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.xtranslate.core"
  compileSdk = 34

  defaultConfig {
    minSdk = 24
    targetSdk = 34

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")

    ndk {
      abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "x86_64"))
    }

    externalNativeBuild {
      cmake {
        cppFlags += "-std=c++17"
        cppFlags += "-fvisibility=hidden"
        cppFlags += "-fvisibility-inlines-hidden"
        arguments += "-DANDROID_STL=c++_shared"
      }
    }
  }

  buildFeatures {
    buildConfig = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  externalNativeBuild {
    cmake {
      path = file("CMakeLists.txt")
      version = "3.22.1"
    }
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)

  // ML Inference
  implementation(libs.onnx.runtime)
  implementation(libs.bundles.tensorflow.lite)

  // Utilities
  implementation(libs.timber)
  implementation(libs.gson)
  implementation(libs.coroutines)

  // Testing
  testImplementation(libs.junit)
  testImplementation(libs.mockito)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}
