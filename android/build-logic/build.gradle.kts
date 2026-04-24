plugins {
  `kotlin-dsl`
}

group = "com.xtranslate.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  compileOnly("com.android.tools.build:gradle:8.2.0")
  compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
}

gradlePlugin {
  plugins {
    register("androidApplicationConvention") {
      id = "xtranslate.android.application"
      implementationClass = "com.xtranslate.buildlogic.AndroidAppConventionPlugin"
    }
    register("androidLibraryConvention") {
      id = "xtranslate.android.library"
      implementationClass = "com.xtranslate.buildlogic.AndroidLibraryConventionPlugin"
    }
    register("kotlinConvention") {
      id = "xtranslate.kotlin"
      implementationClass = "com.xtranslate.buildlogic.KotlinConventionPlugin"
    }
  }
}
