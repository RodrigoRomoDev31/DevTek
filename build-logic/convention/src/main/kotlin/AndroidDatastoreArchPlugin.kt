import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring an Android project with DataStore and serialization support
class AndroidDatastoreArchPlugin : Plugin<Project> {

    // The plugins for configuration
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            // Declare the necessary dependencies for working with DataStore and JSON serialization
            dependencies {
                implementation(libs.library("androidx-datastore"))
                implementation(libs.library("kotlinx-serialization-json"))
            }
        }
    }
}
