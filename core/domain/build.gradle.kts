plugins {
    id("arch.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.romvaz.core.domain"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.retrofit) {
        exclude("module", "okhttp")
    }
}