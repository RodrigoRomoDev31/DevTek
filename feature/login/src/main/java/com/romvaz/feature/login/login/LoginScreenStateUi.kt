package com.romvaz.feature.login.login

import com.romvaz.core.domain.models.network.InternetStatus

// UI state to Login Screen
data class LoginScreenStateUi(
    val loading: Boolean = false,
    val emailInput: String = "",
    val password: String = "",
    val btnEnable: Boolean = false,
    val internetStatus: InternetStatus = InternetStatus.HAVE_CONNECTION
)
