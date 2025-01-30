package com.romvaz.core.domain.routes

interface LoginRoute : Route {

    // Root route for the Login module
    data object RootRoute : LoginRoute {
        override val route = "login"
    }

    // User login route for the Login module
    data object UserLoginRoute : LoginRoute {
        override val route = "login/user_login"
    }
}
