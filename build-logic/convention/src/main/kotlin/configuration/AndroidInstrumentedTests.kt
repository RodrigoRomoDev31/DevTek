package configuration

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

// Extension function to disable unnecessary Android tests based on the existence of the androidTest directory
internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) = beforeVariants {
    it.enableAndroidTest = it.enableAndroidTest
            && project.projectDir.resolve("src/androidTest").exists()
}
