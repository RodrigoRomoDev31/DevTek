import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidTestArchPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(libs.library("androidx-tracing-perfetto-handshake"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("mockito-core"))
                testImplementation(libs.library("mockito-inline"))
                testImplementation(libs.library("kotlinx-coroutines-test"))

                androidTestImplementation(libs.bundle("androidx-test"))
            }
        }
    }
}