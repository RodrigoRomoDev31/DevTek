import com.android.build.api.dsl.ApplicationExtension
import configuration.configureAndroidCompose
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring an Android application module with Jetpack Compose
class AndroidApplicationComposeArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // Apply plugins to set up Android-specific configurations for the application module
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("arch.android.test")
            }

            // Configure the Application extension for Android Compose settings
            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }

            // Declare the necessary dependencies for the application to support Compose and navigation
            dependencies {
                implementation(libs.library("androidx-activity-compose"))
                implementation(libs.library("androidx-navigation-compose"))
            }
        }
    }
}
