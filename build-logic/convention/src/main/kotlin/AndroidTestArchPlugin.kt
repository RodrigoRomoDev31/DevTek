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
                // Add Perfetto handshake library for performance tracing in Android tests
                implementation(libs.library("androidx-tracing-perfetto-handshake"))

                // Add JUnit for unit testing support
                testImplementation(libs.library("junit"))

                // Add Mockito for mocking objects in unit tests (core functionality)
                testImplementation(libs.library("mockito-core"))
                // Add Mockito inline for mocking final classes and methods in unit tests
                testImplementation(libs.library("mockito-inline"))

                // Add Kotlin Coroutines test library to test suspend functions and coroutines
                testImplementation(libs.library("kotlinx-coroutines-test"))

                // Add AndroidX test libraries for instrumentation testing (UI tests, etc.)
                androidTestImplementation(libs.bundle("androidx-test"))
            }
        }
    }
}