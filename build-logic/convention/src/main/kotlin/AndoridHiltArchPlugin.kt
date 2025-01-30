import configuration.extensions.implementation
import configuration.extensions.ksp
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for integrating Hilt dependency injection into an Android project
class AndroidHiltArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // Apply the Dagger Hilt Android plugin for enabling Hilt DI support in Android projects
                apply("dagger.hilt.android.plugin")
            }

            // Declare the necessary dependencies for Hilt and its integration
            dependencies {
                // Add Hilt Android dependency for dependency injection functionality
                implementation(libs.library("hilt-android"))
                // Add Hilt Android compiler dependency for code generation and annotation processing
                ksp(libs.library("hilt-android-compiler"))
                // Add Hilt Navigation Compose dependency for Hilt support in Compose-based navigation
                implementation(libs.library("androidx-hilt-navigation-compose"))
            }
        }
    }
}