package com.romvaz.core.domain.routes

sealed interface LoginRoute : Route {
    data object RootRoute : LoginRoute {
        override val route = "login"
    }

    data object UserLoginRoute : LoginRoute {
        override val route = "login/user_login"
    }

}
