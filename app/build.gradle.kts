plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    //alias(libs.plugins.google.firebase.crashlytics)
    // Add the Crashlytics Gradle plugin
    //id ("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    //implementation(libs.firebase.crashlytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // firebase
    //-implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    //-implementation("com.google.firebase:firebase-analytics")
    // firebase crash analytics
    //-implementation("com.google.firebase:firebase-crashlytics")
    //-implementation("com.google.firebase:firebase-crashlytics-ndk")

    // volley
    implementation("com.android.volley:volley:1.2.1")
    //implementation ("com.google.firebase:firebase-database:15.0.0")
    //implementation ("com.google.firebase:firebase-auth:15.1.0")
}