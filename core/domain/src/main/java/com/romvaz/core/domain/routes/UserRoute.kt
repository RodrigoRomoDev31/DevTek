package com.romvaz.core.domain.routes

sealed interface UserRoute : Route {

    // Root route for the User module
    data object RootRoute : UserRoute {
        override val route = "user"
    }

    // User info route for the User module
    data object UserInfoRoute : UserRoute {
        override val route = "user/user_info"
    }
}
