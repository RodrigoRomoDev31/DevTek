package configuration.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

// Extension property to access the version catalog named "libs" in the project
val Project.libs
    // Retrieves the version catalog named "libs" from the VersionCatalogsExtension
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

