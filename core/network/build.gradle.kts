plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.romvaz.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "API_BASE_URL", "\"https://www.freetogame.com/api/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.retrofit) {
        exclude("module", "okhttp")
    }
}