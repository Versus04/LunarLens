plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    kotlin("plugin.serialization") version "2.1.0"

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.lunarlens"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lunarlens"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    val coil_version = "3.0.4"
    val nav_version = "2.8.5"
    val retrofit_version= "2.11.0"
    val room_version = "2.6.1"
    val dagger_version = "2.51.1"

    //material icons
    implementation("androidx.compose.material:material:1.7.6")

    //Coil
    implementation("io.coil-kt.coil3:coil-compose:${coil_version}")
    implementation("io.coil-kt.coil3:coil-network-okhttp:${coil_version}")

    //Navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${retrofit_version}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofit_version}")

    //Room
    implementation("androidx.room:room-runtime:${room_version}")

    //Dagger
    implementation("com.google.dagger:hilt-android:${dagger_version}")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    //ksp
    ksp("androidx.room:room-compiler:${room_version}")
    ksp("com.google.dagger:hilt-android-compiler:${dagger_version}")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}