import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationArch") {
            id = "arch.android.application"
            implementationClass = "AndroidApplicationArchPlugin"
        }
        register("androidApplicationComposeArch") {
            id = "arch.android.application.compose"
            implementationClass = "AndroidApplicationComposeArchPlugin"
        }
        register("jvmLibraryArch") {
            id = "arch.jvm.library"
            implementationClass = "JvmLibraryArchPlugin"
        }
        register("androidLibraryArch") {
            id = "arch.android.library"
            implementationClass = "AndroidLibraryArchPlugin"
        }
        register("androidLibraryComposeArch") {
            id = "arch.android.library.compose"
            implementationClass = "AndroidLibraryComposeArchPlugin"
        }
        register("androidHiltArch") {
            id = "arch.android.hilt"
            implementationClass = "AndroidHiltArchPlugin"
        }
        register("androidFeatureArch") {
            id = "arch.android.feature"
            implementationClass = "AndroidFeatureArchPlugin"
        }
        register("androidTestArch") {
            id = "arch.android.test"
            implementationClass = "AndroidTestArchPlugin"
        }
        register("androidDatastoreArch") {
            id = "arch.android.datastore"
            implementationClass = "AndroidDatastoreArchPlugin"
        }
    }
}
