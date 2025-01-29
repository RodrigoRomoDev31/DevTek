pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DevTek"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":core:store")
include(":core:ui")
include(":feature:home")
include(":feature:login")
include(":core:data:datastore")
include(":feature:user")
