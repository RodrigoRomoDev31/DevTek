package com.romvaz.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

/**
 * A composable function that observes navigation commands from the [Navigator] and
 * executes them using the provided [NavController].
 *
 * @param navigator The navigation handler that emits navigation commands.
 * @param navController The [NavController] responsible for performing navigation actions.
 */
@Composable
fun NavigatorHandler(
    navigator: Navigator,
    navController: NavController
) {
    // Collect the latest navigation command from the navigator's StateFlow
    val command by navigator.commands.collectAsState()

    // Process the received navigation command
    when (val finalCommand = command) {
        is NavigationCommand.PopBackstack -> {
            // Navigates back to the previous screen in the back stack
            navController.popBackStack()
            navigator.onNavigated() // Notify that navigation has been handled
        }

        is NavigationCommand.PopBackstackUntil -> {
            // Navigates back to a specific destination in the back stack
            navController.popBackStack(
                finalCommand.route, // The destination to pop back to
                finalCommand.inclusive // Whether to remove the destination itself
            )
            navigator.onNavigated()
        }

        is NavigationCommand.NavigateTo -> {
            // Navigates to a new destination using the specified route and options
            navController.navigate(finalCommand.route, finalCommand.navOptions)
            navigator.onNavigated()
        }

        NavigationCommand.Idle -> {
            // No navigation action needed
        }
    }
}
