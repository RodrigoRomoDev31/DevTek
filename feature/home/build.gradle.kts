plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.romvaz.feature.home"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}