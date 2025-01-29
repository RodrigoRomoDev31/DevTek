import org.gradle.api.problems.internal.DefaultLineInFileLocation.from

buildscript {
    dependencies {
        classpath(libs.kotlin.serialization)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        toolVersion = "1.23.5"
        from("$rootDir/detekt-config.yml")
        buildUponDefaultConfig = true
        allRules = false
    }
}