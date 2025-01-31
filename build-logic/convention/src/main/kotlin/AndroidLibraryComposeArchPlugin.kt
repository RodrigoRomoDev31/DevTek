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

    override fun apply(target: Project) {

        // Configuring the target project with the necessary plugins
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("arch.android.test")
            }

            // Configure the Android library extension to include Jetpack Compose settings
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

            // Declare the necessary dependencies for this library
            dependencies {
                implementation(libs.library("androidx-navigation-compose"))
                implementation(libs.library("accompanist-systemuicontroller"))
            }
        }
    }
}