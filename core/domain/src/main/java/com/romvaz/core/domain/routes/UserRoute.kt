package com.romvaz.core.domain.routes

sealed interface UserRoute : Route {
    data object RootRoute : UserRoute {
        override val route = "user"
    }

    data object UserInfoRoute : UserRoute {
        override val route = "user/user_info"
    }

}
