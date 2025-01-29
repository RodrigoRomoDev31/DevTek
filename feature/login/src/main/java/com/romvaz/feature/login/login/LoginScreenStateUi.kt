package com.romvaz.feature.login.login

import com.romvaz.core.ui.components.SnackBarTopStatus

data class LoginScreenStateUi(
    val loading: Boolean = false,
    val emailInput: String = "",
    val password: String = "",
    val btnEnable: Boolean = false,
    val snackBarTopStatus: SnackBarTopStatus = SnackBarTopStatus.ERROR,
    val counter: Int = 0
)
