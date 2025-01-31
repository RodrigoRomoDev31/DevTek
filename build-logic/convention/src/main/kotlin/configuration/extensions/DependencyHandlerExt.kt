package configuration.extensions

import org.gradle.api.artifacts.dsl.DependencyHandler

// Extension function for adding implementation dependencies to the project
fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

// Extension function for adding unit test dependencies to the project
fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

// Extension function for adding Android-specific test dependencies
fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

// Extension function for adding KSP (Kotlin Symbol Processing) dependencies
fun DependencyHandler.ksp(dependency: Any) {
    add("ksp", dependency)
}
