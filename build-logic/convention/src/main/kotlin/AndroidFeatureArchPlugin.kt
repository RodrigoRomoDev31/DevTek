import configuration.extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for configuring feature modules in an Android project with architecture components
class AndroidFeatureArchPlugin : Plugin<Project> {

    // Apply difernet plugins for configuration
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("arch.android.library")
                apply("arch.android.library.compose")
                apply("arch.android.hilt")
            }

            // Declare dependencies for the feature module, linking to core libraries and shared components
            dependencies {
                implementation(project(":core:ui"))
                implementation(project(":core:domain"))
                implementation(project(":core:store"))
            }
        }
    }
}