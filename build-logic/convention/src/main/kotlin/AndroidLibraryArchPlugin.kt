import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import configuration.configureKotlinAndroid
import configuration.disableUnnecessaryAndroidTests
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// This is a custom Gradle Plugin for configuring an Android library project with specific Kotlin and Android components
class AndroidLibraryArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        // Configuring the target project with the necessary plugins
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("arch.android.test")
            }

            // Configure the Android library extension to include Kotlin Android configuration
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                lint.targetSdk = libs.version("targetSDK")
            }

            // Configure the Android Components extension to disable unnecessary Android tests
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }

            // Declare the necessary dependencies for this library
            dependencies {
                implementation(libs.library("kotlinx-coroutines-android"))
            }
        }
    }
}