package com.romvaz.feature.login.login.middlewares

import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.ui.utils.GlobalUtils.validateMail
import com.romvaz.core.ui.utils.GlobalUtils.validatePassword
import com.romvaz.feature.login.login.LoginScreenAction
import com.romvaz.feature.login.login.LoginScreenStateUi

/**
 * Updates the UI state based on the internet status change action.
 *
 * @param action The action that contains the new internet connection status.
 * @return A new [LoginScreenStateUi] instance with the updated internet status and button enabled state.
 */
fun LoginScreenStateUi.onInternetChange(
    action: LoginScreenAction.OnInternetStatus
): LoginScreenStateUi {
    return this.copy(
        internetStatus = action.status,
        btnEnable = validateBtn(this.emailInput, this.password, action.status)
    )
}

/**
 * Updates the UI state based on the email input change action.
 *
 * @param action The action that contains the new email value.
 * @return A new [LoginScreenStateUi] instance with the updated email and button enabled state.
 */
fun LoginScreenStateUi.onEmailChange(
    action: LoginScreenAction.OnEmailChange
): LoginScreenStateUi {
    return this.copy(
        emailInput = action.email,
        btnEnable = validateBtn(action.email, this.password, this.internetStatus)
    )
}

/**
 * Updates the UI state based on the password change action.
 *
 * @param action The action that contains the new password value.
 * @return A new [LoginScreenStateUi] instance with the updated password and button enabled state.
 */
fun LoginScreenStateUi.onPasswordChange(
    action: LoginScreenAction.OnPasswordChange
): LoginScreenStateUi {
    return this.copy(
        password = action.password,
        btnEnable = validateBtn(this.emailInput, action.password, this.internetStatus)
    )
}

/**
 * Validates if the login button should be enabled based on email, password, and internet connection status.
 *
 * @param email The current email value entered by the user.
 * @param password The current password value entered by the user.
 * @param internetStatus The current internet connection status.
 * @return A boolean indicating whether the login button should be enabled.
 */
private fun validateBtn(
    email: String,
    password: String,
    internetStatus: InternetStatus
): Boolean =
    email.validateMail() && internetStatus == InternetStatus.HAVE_CONNECTION && password.validatePassword()

