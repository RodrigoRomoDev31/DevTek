package com.romvaz.feature.home.main.middlewares

import javax.inject.Inject

data class MainMiddlewares @Inject constructor(
    val sendHelpMiddleware: SendHelpMiddleware,
    val userInfoMiddleware: UserInfoMiddleware,
    val getUserLocationMiddleware: GetUserLocationMiddleware,
    val internetServiceMiddleware: InternetServiceMiddleware
)
