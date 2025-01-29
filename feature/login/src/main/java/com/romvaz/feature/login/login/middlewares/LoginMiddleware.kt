package com.romvaz.feature.login.login.middlewares

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.store.ActionSideEffect
import com.romvaz.datastore.services.UserPreferenceService
import com.romvaz.feature.login.login.LoginScreenAction
import com.romvaz.feature.login.login.LoginScreenStateUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginMiddleware @Inject constructor(
    private val appPreferenceService: UserPreferenceService
) : ActionSideEffect<LoginScreenStateUi, LoginScreenAction>() {

    override fun observe(
        actionsFlow: Flow<LoginScreenAction>,
        currentState: () -> LoginScreenStateUi
    ): Flow<LoginScreenAction> =
        actionsFlow
            .filter { it is LoginScreenAction.LoginHardUser }
            .map { appPreferenceService.updateUserInfo(HardUserPreferenceModel(currentState().emailInput)) }
            .map { LoginScreenAction.OnHardLogin }
}

