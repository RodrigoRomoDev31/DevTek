package com.romvaz.feature.home.main.middlewares

import com.romvaz.core.network.connectivity.InternetStatusInterface
import com.romvaz.core.store.StateSideEffect
import com.romvaz.feature.home.main.MainScreenAction
import com.romvaz.feature.home.main.MainScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InternetServiceMiddleware @Inject constructor(
    private val internetStatusInterface: InternetStatusInterface
) : StateSideEffect<MainScreenUiState, MainScreenAction>() {
    override fun observe(stateFlow: Flow<MainScreenUiState>): Flow<MainScreenAction> =
        internetStatusInterface
            .observeInternetStatus
            .distinctUntilChanged()
            .map { MainScreenAction.OnInternetStatus(it) }
}
