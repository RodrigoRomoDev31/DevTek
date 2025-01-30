package com.romvaz.feature.login.login

import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.store.Reducer
import com.romvaz.feature.login.login.middlewares.onEmailChange
import com.romvaz.feature.login.login.middlewares.onInternetChange
import com.romvaz.feature.login.login.middlewares.onPasswordChange

/**
 * Reducer responsible for handling state changes in the Login Screen.
 * It processes [LoginScreenAction] events and updates the [LoginScreenStateUi] accordingly.
 */
class LoginScreenReducer : Reducer<LoginScreenStateUi, LoginScreenAction> {

    override fun reduce(state: LoginScreenStateUi, action: LoginScreenAction): LoginScreenStateUi =
        when (action) {
            // Update email and button state based on the OnEmailChange action
            is LoginScreenAction.OnEmailChange -> state.onEmailChange(action)

            // Update password and button state based on the OnPasswordChange action
            is LoginScreenAction.OnPasswordChange -> state.onPasswordChange(action)

            // Set the loading state to true for LoginHardUser action
            is LoginScreenAction.LoginHardUser -> state.copy(loading = true)

            // Update internet status and button state based on the OnInternetStatus action
            is LoginScreenAction.OnInternetStatus -> state.onInternetChange(action)

            // Return the current state when the action is not recognized
            else -> state
        }
}

/**
 * Represents actions that can trigger state changes in the Login Screen.
 */
sealed interface LoginScreenAction {

    /**
     * Action triggered when the email input changes.
     *
     * @param email The updated email value.
     */
    data class OnEmailChange(
        val email: String
    ) : LoginScreenAction

    /**
     * Action triggered when the password input changes.
     *
     * @param password The updated password value.
     */
    data class OnPasswordChange(
        val password: String
    ) : LoginScreenAction

    /**
     * Action triggered when the internet connection status changes.
     *
     * @param status The new internet connection status.
     */
    data class OnInternetStatus(
        val status: InternetStatus
    ) : LoginScreenAction

    /**
     * Action triggered to initiate the "hard" login process.
     */
    data object LoginHardUser : LoginScreenAction

    /**
     * Action triggered for a generic hard login response.
     */
    data object OnHardLogin : LoginScreenAction
}
