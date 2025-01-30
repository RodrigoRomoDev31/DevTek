package configuration.extensions

import org.gradle.api.artifacts.VersionCatalog

// Extension function to retrieve the version of a dependency as an integer from the version catalog
fun VersionCatalog.version(alias: String) = findVersion(alias).get().toString().toInt()

// Extension function to retrieve a bundle of dependencies from the version catalog
fun VersionCatalog.bundle(alias: String) = findBundle(alias).get()

// Extension function to retrieve a library dependency from the version catalog
fun VersionCatalog.library(alias: String) = findLibrary(alias).get()