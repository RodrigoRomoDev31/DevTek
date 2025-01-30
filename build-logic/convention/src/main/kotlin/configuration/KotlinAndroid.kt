package configuration

import com.android.build.api.dsl.CommonExtension
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.version
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Extension function to configure Kotlin for Android projects
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        // Set the target SDK version for compiling the project
        compileSdk = libs.version("targetSDK")

        // Configure default project settings for the Android app
        defaultConfig {
            // Set the minimum SDK version required for the app
            minSdk = libs.version("minSDK")

            // Define the test instrumentation runner to use for Android unit and UI tests
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        // Set the Java version compatibility for the project
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        // Apply the shared Kotlin configuration for compiling Kotlin code
        configureKotlin()

        // Add the necessary dependencies for Android KTX extensions
        dependencies {
            // Provides Kotlin extensions for Android components (e.g., for easier use of Android APIs)
            implementation(libs.library("androidx-core-ktx"))
        }
    }
}

// Extension function to configure Kotlin for JVM projects
internal fun Project.configureKotlinJvm() {
    // Set Java version compatibility for a JVM-based project
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Apply the shared Kotlin configuration for compiling Kotlin code
    configureKotlin()
}

// Shared function to configure Kotlin compilation settings
private fun Project.configureKotlin() {
    // Ensure that Kotlin compile tasks use JVM 11 as the target version for the bytecode
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            // Set the Kotlin JVM target to 11
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}
