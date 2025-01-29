plugins {
    id("arch.android.library")
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
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.retrofit) {
        exclude("module", "okhttp")
    }
}