package configuration

import com.android.build.api.dsl.CommonExtension
import configuration.extensions.bundle
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// Extension function to configure Android Compose settings for the project
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    // Apply the necessary Compose configurations within the provided CommonExtension
    // Enable the Compose build feature to include Compose support in the project
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        // Add dependencies necessary for Compose functionality
        dependencies {
            val bom = libs.library("androidx-compose-bom")
            implementation(platform(bom))
            implementation(libs.bundle("androidx-compose"))
            implementation(libs.bundle("androidx-lifecycle-compose"))
        }
    }
}