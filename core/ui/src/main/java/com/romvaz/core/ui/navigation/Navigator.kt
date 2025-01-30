package com.romvaz.core.ui.navigation

import kotlinx.coroutines.flow.StateFlow

/**
 * Interface responsible for handling navigation commands within the application.
 */
interface Navigator {

    /**
     * A [StateFlow] that emits navigation commands to be observed and processed by the navigation system.
     */
    val commands: StateFlow<NavigationCommand>

    /**
     * Triggers a navigation event with the given [command].
     *
     * @param command The navigation command to be executed.
     */
    fun navigate(command: NavigationCommand)

    /**
     * Notifies that navigation has been handled, allowing the system to reset or update state accordingly.
     */
    fun onNavigated()
}
