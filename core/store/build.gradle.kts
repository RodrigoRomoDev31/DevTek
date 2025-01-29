plugins {
    id("arch.android.library")
}

android {
    namespace = "com.romvaz.core.store"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}