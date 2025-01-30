package com.romvaz.feature.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.romvaz.core.domain.routes.LoginRoute
import com.romvaz.feature.login.login.LoginScreen

// Navigation Graph for Login Screen
fun NavGraphBuilder.loginGraph() {
    navigation(
        startDestination = LoginRoute.UserLoginRoute.route,
        route = LoginRoute.RootRoute.route
    ) {
        composable(
            route = LoginRoute.UserLoginRoute.route,
        ) {
            LoginScreen()
        }
    }
}
