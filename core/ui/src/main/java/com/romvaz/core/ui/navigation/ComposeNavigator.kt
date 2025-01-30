package com.romvaz.core.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Implementation of the [Navigator] interface using Jetpack Compose's state management.
 * It manages navigation commands via a [MutableStateFlow] and ensures navigation events
 * are properly handled.
 */
class ComposeNavigator : Navigator {

    // StateFlow to hold the current navigation command, initially set to Idle
    private val commandFlow: MutableStateFlow<NavigationCommand> =
        MutableStateFlow(NavigationCommand.Idle)

    /**
     * Exposes the current navigation command as a read-only [StateFlow].
     */
    override val commands
        get() = commandFlow.asStateFlow()

    /**
     * Updates the current navigation command, triggering navigation actions.
     *
     * @param command The navigation command to be executed.
     */
    override fun navigate(command: NavigationCommand) {
        commandFlow.update { command }
    }

    /**
     * Resets the navigation command to [NavigationCommand.Idle] after navigation is handled.
     * This ensures that the same command is not processed multiple times.
     */
    override fun onNavigated() {
        commandFlow.update { NavigationCommand.Idle }
    }
}
