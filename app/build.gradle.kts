plugins {
    id("arch.android.application")
    id("arch.android.application.compose")
    id("arch.android.hilt")
}

android {
    namespace = "com.romvaz.devtek"

    defaultConfig {
        applicationId = "com.romvaz.devtek"
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:user"))
}