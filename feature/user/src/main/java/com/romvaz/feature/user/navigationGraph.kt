package com.romvaz.feature.user

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.romvaz.core.domain.routes.UserRoute
import com.romvaz.feature.user.info.UserScreen

fun NavGraphBuilder.userGraph() {
    navigation(
        startDestination = UserRoute.UserInfoRoute.route,
        route = UserRoute.RootRoute.route
    ) {
        composable(
            route = UserRoute.UserInfoRoute.route,
        ) {
            UserScreen()
        }
    }
}
