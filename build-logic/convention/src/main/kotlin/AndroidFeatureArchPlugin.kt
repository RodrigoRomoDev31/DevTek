import configuration.extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("arch.android.library")
                apply("arch.android.library.compose")
                apply("arch.android.hilt")
            }

            dependencies {
                implementation(project(":core:ui"))
                implementation(project(":core:domain"))
                implementation(project(":core:store"))
                implementation(project(":core:network"))
            }
        }
    }
}