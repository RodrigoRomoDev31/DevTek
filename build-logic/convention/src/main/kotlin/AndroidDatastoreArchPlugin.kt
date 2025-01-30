import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring an Android project with DataStore and serialization support
class AndroidDatastoreArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // Apply custom Android library plugin
                apply("arch.android.library")
                // Apply Kotlin Serialization plugin to enable JSON serialization support
                apply("org.jetbrains.kotlin.plugin.serialization")
                // Apply custom Hilt plugin for dependency injection
                apply("arch.android.hilt")
            }

            // Declare the necessary dependencies for working with DataStore and JSON serialization
            dependencies {
                // Add DataStore dependency to manage key-value storage in the app
                implementation(libs.library("androidx-datastore"))
                // Add Kotlin Serialization JSON library for serializing and deserializing data to/from JSON
                implementation(libs.library("kotlinx-serialization-json"))
            }
        }
    }
}
