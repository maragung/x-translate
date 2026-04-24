# Proguard configuration for x-translate

# General rules
-verbose
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses,EnclosingMethod

# Keep all native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# ONNX Runtime
-keep class com.microsoft.onnxruntime.** { *; }
-keep interface com.microsoft.onnxruntime.** { *; }

# TensorFlow Lite
-keep class org.tensorflow.lite.** { *; }

# Room Database
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao interface *

# Hilt Dependency Injection
-keep class dagger.** { *; }
-keep class com.google.dagger.** { *; }
-keep class javax.inject.** { *; }
-keep @dagger.hilt.** class *
-keep @javax.inject.** class *

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }
-keepclassmembers class kotlin.Metadata {
    *** invoke(...);
}
-dontwarn kotlin.**

# Serialization
-keep class com.google.gson.** { *; }
-keep interface com.google.gson.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# Logging
-keep class com.jakewharton.timber.** { *; }

# Android components
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Model classes
-keep class com.xtranslate.data.model.** { <init>(...); }
-keep class com.xtranslate.viewmodel.** { *; }

# Lifecycle
-keep class androidx.lifecycle.** { *; }
-keep class * implements androidx.lifecycle.** { *; }

# View classes
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Drawable resources
-dontwarn android.support.v4.graphics.**

# Suppress warnings
-dontwarn java.lang.invoke.StringConcatFactory
-dontwarn javax.annotation.**
-dontwarn sun.misc.Unsafe
-dontwarn com.sun.**
