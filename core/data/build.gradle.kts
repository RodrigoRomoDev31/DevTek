plugins {
    id("arch.android.library")
    id("arch.android.hilt")
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
    implementation(project(":core:data:datastore"))

    implementation(libs.retrofit) {
        exclude("module", "okhttp")
    }
}