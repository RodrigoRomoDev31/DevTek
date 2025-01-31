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

    // Apply different plugins to configure Android app-specific settings
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("arch.android.test")
            }

            // Configure the Application extension for specific Android application settings
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.version("targetSDK")
            }

            // Declare the necessary dependencies for the application
            dependencies {
                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
            }
        }
    }
}