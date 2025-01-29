package com.romvaz.core.domain.routes

sealed interface HomeRoute : Route {

    data object RootRoute : HomeRoute {
        override val route = "home"
    }

    data object SplashRoute : HomeRoute {
        override val route = "home/splash"
    }

    data object MainRoute : HomeRoute {
        override val route = "home/main"
    }
}
