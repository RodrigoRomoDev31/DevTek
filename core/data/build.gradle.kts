plugins {
    id("arch.android.library")
    id("arch.android.hilt")
    id("arch.android.datastore")
    id("arch.android.test")
}

android {
    namespace = "com.romvaz.core.data"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))

    implementation(libs.play.services.location)
    implementation(libs.retrofit) {
        exclude("module", "okhttp")
    }
}