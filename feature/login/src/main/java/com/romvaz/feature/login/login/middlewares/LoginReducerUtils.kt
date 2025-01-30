package com.romvaz.feature.login.login.middlewares

import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.ui.components.validateMail
import com.romvaz.feature.login.login.LoginScreenAction
import com.romvaz.feature.login.login.LoginScreenStateUi

fun LoginScreenStateUi.onInternetChange(
    action: LoginScreenAction.OnInternetStatus
): LoginScreenStateUi {
    return this.copy(
        internetStatus = action.status,
        btnEnable = validateBtn(this.emailInput, this.password, action.status)
    )
}

fun LoginScreenStateUi.onEmailChange(
    action: LoginScreenAction.OnEmailChange
): LoginScreenStateUi {
    return this.copy(
        emailInput = action.email,
        btnEnable = validateBtn(action.email, this.password, this.internetStatus)
    )
}

fun LoginScreenStateUi.onPasswordChange(
    action: LoginScreenAction.OnPasswordChange
): LoginScreenStateUi {
    return this.copy(
        password = action.password,
        btnEnable = validateBtn(this.emailInput, action.password, this.internetStatus)
    )
}

private fun validateBtn(
    email: String,
    password: String,
    internetStatus: InternetStatus
): Boolean =
    email.validateMail() && internetStatus == InternetStatus.HAVE_CONNECTION && password.isNotEmpty()
