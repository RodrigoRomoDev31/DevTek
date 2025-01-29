plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.romvaz.feature.login"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}
