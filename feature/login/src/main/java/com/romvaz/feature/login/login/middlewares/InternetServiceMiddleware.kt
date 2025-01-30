package com.romvaz.feature.login.login.middlewares

import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.core.store.StateSideEffect
import com.romvaz.feature.login.login.LoginScreenAction
import com.romvaz.feature.login.login.LoginScreenStateUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Middleware to observe changes in Internet Connectivity
// Triggers and LoginAction Every time it changes
class InternetServiceMiddleware @Inject constructor(
    private val internetStatusService: InternetStatusService
) : StateSideEffect<LoginScreenStateUi, LoginScreenAction>() {
    override fun observe(stateFlow: Flow<LoginScreenStateUi>): Flow<LoginScreenAction> =
        internetStatusService
            .observeInternetStatus
            .distinctUntilChanged()
            .map { LoginScreenAction.OnInternetStatus(it) }
}
