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

/**
 * Extension function to configure Kotlin for Android projects
 *  Set the target SDK version for compiling the project
 *  Configure default project settings for the Android app
 *  Set the minimum SDK version required for the app
 *  Define the test instrumentation runner to use for Android unit and UI tests
 *  Set the Java version compatibility for the project
 *  Apply the shared Kotlin configuration for compiling Kotlin code
 *  Add the necessary dependencies for Android KTX extensions
 *  Add the necessary dependencies for Android KTX extensions
 *  Set Java version compatibility for a JVM-based project
 *  Shared function to configure Kotlin compilation settings
  */

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = libs.version("targetSDK")

        defaultConfig {
            minSdk = libs.version("minSDK")

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        configureKotlin()

        dependencies {
            implementation(libs.library("androidx-core-ktx"))
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}
