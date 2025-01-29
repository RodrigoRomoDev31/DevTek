package com.romvaz.core.ui.navigation

import androidx.navigation.NavOptions
import com.romvaz.core.domain.routes.Route

sealed interface NavigationCommand {

    data object Idle : NavigationCommand

    data object PopBackstack : NavigationCommand

    data class PopBackstackUntil(
        val route: String,
        val inclusive: Boolean = false
    ) : NavigationCommand

    data class NavigateTo(
        val route: String,
        val navOptions: NavOptions? = null,
    ) : NavigationCommand
}


fun Route.asNavigationCommand(navOptions: NavOptions? = null): NavigationCommand =
    NavigationCommand.NavigateTo(
        route, navOptions
    )
