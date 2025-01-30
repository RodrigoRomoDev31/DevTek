import com.android.build.api.dsl.ApplicationExtension
import configuration.configureAndroidCompose
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring an Android application module with Jetpack Compose and architecture-related components
class AndroidApplicationComposeArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // Apply the Android Application plugin to set up Android-specific configurations for the application module
                apply("com.android.application")
                // Apply the Kotlin plugin for Compose to enable Jetpack Compose support
                apply("org.jetbrains.kotlin.plugin.compose")
                // Apply custom architecture plugin
                apply("arch.android.test")
            }

            // Configure the Application extension for Android Compose settings
            extensions.configure<ApplicationExtension> {
                // This function configures the Android Compose settings, enabling Compose-related features
                configureAndroidCompose(this)
            }

            // Declare the necessary dependencies for the application to support Compose and navigation
            dependencies {
                // Add Activity Compose dependency for integrating Jetpack Compose with Activity components
                implementation(libs.library("androidx-activity-compose"))
                // Add Navigation Compose dependency for handling navigation in Compose-based UIs
                implementation(libs.library("androidx-navigation-compose"))
            }
        }
    }
}
