plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.romvaz.feature.user"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}
