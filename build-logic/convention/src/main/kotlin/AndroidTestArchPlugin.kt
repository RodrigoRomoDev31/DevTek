import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Custom plugin for setting up Android testing dependencies in a project
class AndroidTestArchPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project
    override fun apply(target: Project) {
        with(target) {

            // Declare dependencies related to unit and instrumentation tests
            dependencies {
                implementation(libs.library("androidx-tracing-perfetto-handshake"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("mockito-core"))
                testImplementation(libs.library("mockito-inline"))
                testImplementation(libs.library("mockito-kotlin"))
                testImplementation(libs.library("mockito-io"))
                testImplementation(libs.library("kotlinx-coroutines-test"))
                implementation(libs.library("androidx-junit-ktx"))
                androidTestImplementation(libs.bundle("androidx-test"))
            }
        }
    }
}