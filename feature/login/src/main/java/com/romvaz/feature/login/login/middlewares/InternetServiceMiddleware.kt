package com.romvaz.feature.login.login.middlewares

import com.romvaz.core.network.connectivity.InternetStatusInterface
import com.romvaz.core.store.StateSideEffect
import com.romvaz.feature.login.login.LoginScreenAction
import com.romvaz.feature.login.login.LoginScreenStateUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InternetServiceMiddleware @Inject constructor(
    private val internetStatusInterface: InternetStatusInterface
) : StateSideEffect<LoginScreenStateUi, LoginScreenAction>() {
    override fun observe(stateFlow: Flow<LoginScreenStateUi>): Flow<LoginScreenAction> =
        internetStatusInterface
            .observeInternetStatus
            .distinctUntilChanged()
            .map { LoginScreenAction.OnInternetStatus(it) }
}
