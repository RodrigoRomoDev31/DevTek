import configuration.extensions.implementation
import configuration.extensions.ksp
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for integrating Hilt dependency injection into an Android project
class AndroidHiltArchPlugin : Plugin<Project> {

    // Apply the Dagger Hilt Android plugin for enabling Hilt DI support in Android projects
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
            }

            // Declare the necessary dependencies for Hilt and its integration
            dependencies {
                implementation(libs.library("hilt-android"))
                ksp(libs.library("hilt-android-compiler"))
                implementation(libs.library("androidx-hilt-navigation-compose"))
            }
        }
    }
}