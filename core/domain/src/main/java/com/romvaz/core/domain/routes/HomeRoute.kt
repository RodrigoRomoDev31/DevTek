package com.romvaz.core.domain.routes

sealed interface HomeRoute : Route {

    // Root route for the Home module
    data object RootRoute : HomeRoute {
        override val route = "home"
    }

    // Splash route for the Home module
    data object SplashRoute : HomeRoute {
        override val route = "home/splash"
    }

    // Main route for the Home module
    data object MainRoute : HomeRoute {
        override val route = "home/main"
    }
}
