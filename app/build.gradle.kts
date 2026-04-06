import java.util.Properties
import org.gradle.api.GradleException

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) load(file.inputStream())
}

fun localProperty(key: String, default: String): String {
    val result = localProperties.getProperty(key)
    result?.let { return it }
    logger.lifecycle("local.properties: $key is null, using '$default'")
    return default
}

fun requireLocalProperty(key: String): String {
    val raw = localProperties.getProperty(key)?.trim()
    if (!raw.isNullOrEmpty()) return raw
    throw GradleException(
        "Please add \"$key\" в ${rootProject.file("local.properties").path}\n" +
                "For example: $key=https://api.example.com/demo/"
    )
}

fun devBackendUrl(): String {
    val defaultProfile = "emulator"
    val defaultUrl = "http://10.0.2.2:8189/demo/"
    val profile = localProperty("DEV_BACKEND_PROFILE", defaultProfile).lowercase()
    val byProfile = mapOf(
        defaultProfile to localProperty("LOCAL_BACKEND_URL_DEV_EMULATOR", defaultUrl),
        "home" to localProperty("LOCAL_BACKEND_URL_DEV_HOME", defaultUrl),
        "lan" to localProperty("LOCAL_BACKEND_URL_DEV_LAN", defaultUrl)
    )
    val url = byProfile[profile]
    when {
        url == null -> logger.lifecycle("Unknown DEV_BACKEND_PROFILE='$profile', using '$defaultProfile'")
        url.isBlank() -> logger.lifecycle("local.properties: LOCAL_BACKEND_URL_DEV_${profile.uppercase()} is empty, using '$defaultUrl'")
        else -> return url
    }
    return defaultUrl
}

android {
    namespace = "com.example.geoguesser"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.geoguesser"
        minSdk = 24
        targetSdk = 36
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
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            val url = devBackendUrl()
            buildConfigField("String", "BASE_URL", "\"$url\"")
        }
        create("prod") {
            dimension = "environment"
            val url = requireLocalProperty("LOCAL_BACKEND_URL_PROD")
            buildConfigField("String", "BASE_URL", "\"$url\"")
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
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    // Debug-only
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.leakcanary.android)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // Serialization
    implementation(libs.gson)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // Serialization
    implementation(libs.gson)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
}