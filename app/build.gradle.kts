plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "edu.sfsu.times"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.sfsu.times"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    val roomVersion = "2.6.1"
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
    annotationProcessor(libs.room.compiler)
    implementation(libs.activity)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.ext)
    implementation(libs.lifecycle)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.material)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.okhttp)
    implementation(libs.picasso)
    implementation(libs.room.common)
    implementation(libs.room.guava)     // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.room.ktx)       // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.paging)    // optional - Paging 3 Integration
    implementation(libs.room.runtime)
    implementation(libs.room.rxjava2)   // optional - RxJava2 support for Room
    implementation(libs.room.rxjava3)   // optional - RxJava3 support for Room
    testImplementation(libs.junit)
    testImplementation(libs.room.testing) // optional - Test helpers
    // To use Kotlin annotation processing tool (kapt)
    // kapt("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin Symbol Processing (KSP)
    // ksp("androidx.room:room-compiler:$roomVersion")
}