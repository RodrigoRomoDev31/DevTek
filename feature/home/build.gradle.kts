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

dependencies {
    implementation(libs.maps.compose)
    implementation (libs.play.services.maps)
    implementation (libs.play.services.location)
}
