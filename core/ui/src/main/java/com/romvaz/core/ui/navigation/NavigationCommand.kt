package com.romvaz.core.ui.navigation

import androidx.navigation.NavOptions

/**
 * A sealed interface representing different types of navigation commands.
 */
sealed interface NavigationCommand {

    /**
     * Represents an idle state where no navigation action is needed.
     */
    data object Idle : NavigationCommand

    /**
     * Represents a navigation command to pop the back stack, navigating to the previous screen.
     */
    data object PopBackstack : NavigationCommand

    /**
     * Represents a navigation command to pop the back stack until a specific destination.
     *
     * @param route The destination to navigate back to.
     * @param inclusive If true, the specified destination is also removed from the back stack.
     */
    data class PopBackstackUntil(
        val route: String,
        val inclusive: Boolean = false
    ) : NavigationCommand

    /**
     * Represents a navigation command to navigate to a specific destination.
     *
     * @param route The destination route to navigate to.
     * @param navOptions Optional navigation options such as animations or launch behavior.
     */
    data class NavigateTo(
        val route: String,
        val navOptions: NavOptions? = null,
    ) : NavigationCommand
}
