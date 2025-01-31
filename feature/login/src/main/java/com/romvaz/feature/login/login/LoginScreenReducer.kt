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
            is LoginScreenAction.OnEmailChange -> state.onEmailChange(action)

            is LoginScreenAction.OnPasswordChange -> state.onPasswordChange(action)

            is LoginScreenAction.LoginHardUser -> state.copy(loading = true)

            is LoginScreenAction.OnInternetStatus -> state.onInternetChange(action)

            else -> state
        }
}

/**
 * Represents actions that can trigger state changes in the Login Screen.
 */
sealed interface LoginScreenAction {

    data class OnEmailChange(
        val email: String
    ) : LoginScreenAction

    data class OnPasswordChange(
        val password: String
    ) : LoginScreenAction

    data class OnInternetStatus(
        val status: InternetStatus
    ) : LoginScreenAction

    data object LoginHardUser : LoginScreenAction

    data object OnHardLogin : LoginScreenAction
}
