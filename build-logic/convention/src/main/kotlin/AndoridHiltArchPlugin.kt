import configuration.extensions.implementation
import configuration.extensions.ksp
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltArchPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                implementation(libs.library("hilt-android"))
                ksp(libs.library("hilt-android-compiler"))
                implementation(libs.library("androidx-hilt-navigation-compose"))
            }
        }
    }
}