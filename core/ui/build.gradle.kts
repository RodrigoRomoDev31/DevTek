plugins {
    id("arch.android.library")
    id("arch.android.library.compose")
}

android {
    namespace = "com.romvaz.core.ui"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
}