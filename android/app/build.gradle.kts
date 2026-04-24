plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id("kotlin-kapt")
  alias(libs.plugins.hilt.android)
}

android {
  namespace = "com.xtranslate"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.xtranslate"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }

    // NDK configuration for native builds
    ndk {
      abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "x86_64"))
    }

    // CMake configuration
    externalNativeBuild {
      cmake {
        cppFlags += "-std=c++17"
        cFlags += "-std=c11"
        arguments += "-DANDROID_STL=c++_shared"
        arguments += "-DANDROID_PLATFORM=android-24"
      }
    }
  }

  // Split APK per ABI
  splits {
    abi {
      isEnable = true
      reset()
      include("armeabi-v7a", "arm64-v8a", "x86_64")
      isUniversalApk = true
    }
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.8"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
  }

  buildTypes {
    debug {
      isDebuggable = true
      isMinifyEnabled = false
      applicationIdSuffix = ".debug"
      versionNameSuffix = "-debug"
    }

    release {
      isDebuggable = false
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("debug")  // Replace with release config
    }
  }

  externalNativeBuild {
    cmake {
      path = file("CMakeLists.txt")
      version = "3.22.1"
    }
  }

  packagingOptions {
    exclude("META-INF/proguard/androidx-*.pro")
    exclude("META-INF/licenses/**")
  }
}

dependencies {
  // Core Android libraries
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)

  // Jetpack Compose
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose)
  implementation(libs.androidx.lifecycle.viewmodel.compose)

  // Room Database
  implementation(libs.bundles.room)
  kapt(libs.androidx.room.compiler)

  // Hilt Dependency Injection
  implementation(libs.bundles.hilt)
  kapt(libs.hilt.android.compiler)

  // ML Inference
  implementation(libs.onnx.runtime)
  implementation(libs.bundles.tensorflow.lite)

  // Utilities
  implementation(libs.timber)
  implementation(libs.okhttp)
  implementation(libs.gson)
  implementation(libs.coroutines)

  // Testing
  testImplementation(libs.junit)
  testImplementation(libs.mockito)
  testImplementation(libs.mockito.kotlin)

  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui)

  // Core module
  implementation(project(":core"))
}

// Handle signing for release builds
if (file("../keystore.properties").exists()) {
  val keystoreProperties = java.util.Properties()
  keystoreProperties.load(java.io.FileInputStream(file("../keystore.properties")))

  android.signingConfigs {
    create("release") {
      keyAlias = keystoreProperties["keyAlias"] as String
      keyPassword = keystoreProperties["keyPassword"] as String
      storeFile = file(keystoreProperties["storeFile"] as String)
      storePassword = keystoreProperties["storePassword"] as String
    }
  }

  android.buildTypes {
    getByName("release") {
      signingConfig = android.signingConfigs.getByName("release")
    }
  }
}
