package com.xtranslate

import android.app.Application
import com.google.dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
  override fun onCreate() {
    super.onCreate()

    // Initialize logging
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }

    Timber.d("App initialized")
  }
}

private class CrashReportingTree : Timber.Tree() {
  override fun log(
    priority: Int,
    tag: String?,
    message: String,
    t: Throwable?
  ) {
    if (priority == Timber.ERROR || priority == Timber.WARN) {
      // Future: Send to crash reporting service
      t?.printStackTrace()
    }
  }
}
