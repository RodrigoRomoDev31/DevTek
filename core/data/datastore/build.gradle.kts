plugins {
    id("arch.android.datastore")
}

android {
    namespace = "com.romvaz.datastore"
}

dependencies {
    implementation(project(":core:domain"))
}

