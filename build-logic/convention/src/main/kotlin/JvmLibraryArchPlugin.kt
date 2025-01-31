import configuration.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

// Custom plugin for setting up a Kotlin JVM library project with necessary configurations
class JvmLibraryArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }

            // Configure Kotlin for JVM, applying necessary compiler options and settings
            configureKotlinJvm()
        }
    }
}