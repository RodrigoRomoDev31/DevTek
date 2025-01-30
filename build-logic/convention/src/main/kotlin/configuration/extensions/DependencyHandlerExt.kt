package configuration.extensions

import org.gradle.api.artifacts.dsl.DependencyHandler

// Extension function for adding implementation dependencies to the project
fun DependencyHandler.implementation(dependency: Any) {
    // Adds the given dependency to the 'implementation' configuration
    add("implementation", dependency)
}

// Extension function for adding unit test dependencies to the project
fun DependencyHandler.testImplementation(dependency: Any) {
    // Adds the given dependency to the 'testImplementation' configuration for unit tests
    add("testImplementation", dependency)
}

// Extension function for adding Android-specific test dependencies
fun DependencyHandler.androidTestImplementation(dependency: Any) {
    // Adds the given dependency to the 'androidTestImplementation' configuration for instrumentation tests
    add("androidTestImplementation", dependency)
}

// Extension function for adding KSP (Kotlin Symbol Processing) dependencies
fun DependencyHandler.ksp(dependency: Any) {
    // Adds the given dependency to the 'ksp' configuration for Kotlin Symbol Processing
    add("ksp", dependency)
}
