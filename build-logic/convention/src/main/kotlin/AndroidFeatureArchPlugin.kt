import configuration.extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring feature modules in an Android project with architecture components
class AndroidFeatureArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // Apply custom Android library plugin
                apply("arch.android.library")
                // Apply custom Android library plugin for Compose support
                apply("arch.android.library.compose")
                // Apply custom Hilt plugin for Dependency Injection support
                apply("arch.android.hilt")
            }

            // Declare dependencies for the feature module, linking to core libraries and shared components
            dependencies {
                // Add core UI module for shared UI components
                implementation(project(":core:ui"))
                // Add core domain module for shared domain logic (use cases, business rules, etc.)
                implementation(project(":core:domain"))
                // Add core store module for shared data storage components
                implementation(project(":core:store"))
            }
        }
    }
}