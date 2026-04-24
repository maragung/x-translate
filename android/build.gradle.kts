// Root build.gradle.kts

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.hilt.android) apply false
  id("org.jetbrains.kotlin.plugin.kapt") version "1.9.22" apply false
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
