import com.android.build.api.dsl.LibraryExtension
import configuration.configureAndroidCompose
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// This is a custom Gradle Plugin for configuring an Android library project with Jetpack Compose and architecture-related components
class AndroidLibraryComposeArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        // Configuring the target project with the necessary plugins
        with(target) {
            with(pluginManager) {
                // Apply Android Library plugin for Android-specific configurations
                apply("com.android.library")
                // Apply Kotlin plugin for Compose to enable Jetpack Compose support in the library
                apply("org.jetbrains.kotlin.plugin.compose")
                // Apply custom plugin for Android architecture
                apply("arch.android.test")
            }

            // Configure the Android library extension to include Jetpack Compose settings
            extensions.configure<LibraryExtension> {
                // This function configures Android Compose settings, such as enabling Compose UI features
                configureAndroidCompose(this)
            }

            // Declare the necessary dependencies for this library
            dependencies {
                // Add the Navigation Compose library as an implementation dependency for navigation in Compose-based UI
                implementation(libs.library("androidx-navigation-compose"))
                // Add Accompanist's System UI Controller for controlling system UI elements in a Compose app
                implementation(libs.library("accompanist-systemuicontroller"))
            }
        }
    }
}