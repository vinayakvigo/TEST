// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
    //alias(libs.plugins.google.firebase.crashlytics) apply false
    // Add the dependency for the Crashlytics Gradle plugin
    //-id("com.google.firebase.crashlytics") version "3.0.0" apply false
}