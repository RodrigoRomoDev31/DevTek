import com.android.build.api.dsl.ApplicationExtension
import configuration.configureKotlinAndroid
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring the main Android application module with essential components
class AndroidApplicationArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // Apply the Android Application plugin to configure Android app-specific settings
                apply("com.android.application")
                // Apply the Kotlin Android plugin to support Kotlin in Android projects
                apply("org.jetbrains.kotlin.android")
                // Apply the KSP (Kotlin Symbol Processing) plugin for code generation tasks
                apply("com.google.devtools.ksp")
                // Apply custom plugin for Android architecture-related tasks
                apply("arch.android.test")
            }

            // Configure the Application extension for specific Android application settings
            extensions.configure<ApplicationExtension> {
                // Configure Kotlin Android settings, such as source sets for Kotlin code
                configureKotlinAndroid(this)
                // Set the target SDK version from the provided libs version
                defaultConfig.targetSdk = libs.version("targetSDK")
            }

            // Declare the necessary dependencies for the application
            dependencies {
                // Add Lifecycle runtime dependency for managing lifecycle-aware components (e.g., ViewModel, LiveData)
                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
            }
        }
    }
}