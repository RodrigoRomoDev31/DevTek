import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import configuration.configureKotlinAndroid
import configuration.disableUnnecessaryAndroidTests
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("arch.android.test")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                lint.targetSdk = libs.version("targetSDK")
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }

            dependencies {
                implementation(libs.library("kotlinx-coroutines-android"))
            }
        }
    }
}