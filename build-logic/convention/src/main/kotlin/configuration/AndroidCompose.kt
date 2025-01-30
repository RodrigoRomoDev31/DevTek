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
    commonExtension.apply {
        // Enable the Compose build feature to include Compose support in the project
        buildFeatures {
            compose = true
        }

        // Add dependencies necessary for Compose functionality
        dependencies {
            // Get the BOM (Bill of Materials) for Compose to ensure consistent versioning across dependencies
            val bom = libs.library("androidx-compose-bom")
            // Apply the BOM for the Compose dependencies
            implementation(platform(bom))
            // Add the core Compose UI components from the Compose library bundle
            implementation(libs.bundle("androidx-compose"))
            // Add the Lifecycle Compose library for integrating Compose with Android Lifecycle components
            implementation(libs.bundle("androidx-lifecycle-compose"))
        }
    }
}