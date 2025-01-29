package com.romvaz.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.feature.home.main.MainScreen
import com.romvaz.feature.home.splash.SplashScreen

fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = HomeRoute.SplashRoute.route,
        route = HomeRoute.RootRoute.route
    ) {
        composable(
            HomeRoute.SplashRoute.route
        ) {
            SplashScreen()
        }

        composable(HomeRoute.MainRoute.route) {
            MainScreen()
        }
    }
}
