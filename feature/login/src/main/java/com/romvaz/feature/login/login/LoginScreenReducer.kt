package com.romvaz.feature.login.login

import com.romvaz.core.store.Reducer
import com.romvaz.core.ui.components.validateMail

class LoginScreenReducer : Reducer<LoginScreenStateUi, LoginScreenAction> {
    override fun reduce(state: LoginScreenStateUi, action: LoginScreenAction): LoginScreenStateUi =
        when (action) {
            is LoginScreenAction.OnEmailChange -> state.onEmailChange(action)
            is LoginScreenAction.OnPasswordChange -> state.copy(password = action.password)
            is LoginScreenAction.LoginHardUser -> state.copy(loading = true)
            else -> state
        }

    private fun LoginScreenStateUi.onEmailChange(
        action: LoginScreenAction.OnEmailChange
    ): LoginScreenStateUi {
        return this.copy(
            emailInput = action.email,
            btnEnable = action.email.validateMail()
        )
    }
}

sealed interface LoginScreenAction {
    data class OnEmailChange(
        val email: String
    ) : LoginScreenAction

    data class OnPasswordChange(
        val password: String
    ) : LoginScreenAction

    data object LoginHardUser : LoginScreenAction
    data object OnHardLogin : LoginScreenAction
}
