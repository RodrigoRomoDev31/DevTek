import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDatastoreArchPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("arch.android.library")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("arch.android.hilt")
            }

            dependencies {
                implementation(libs.library("androidx-datastore"))
                implementation(libs.library("kotlinx-serialization-json"))
            }
        }
    }
}
