package com.romvaz.feature.home.main.middlewares

import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.core.store.StateSideEffect
import com.romvaz.feature.home.main.MainScreenAction
import com.romvaz.feature.home.main.MainScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InternetServiceMiddleware @Inject constructor(
    private val internetStatusService: InternetStatusService
) : StateSideEffect<MainScreenUiState, MainScreenAction>() {
    override fun observe(stateFlow: Flow<MainScreenUiState>): Flow<MainScreenAction> =
        internetStatusService
            .observeInternetStatus
            .distinctUntilChanged()
            .map { MainScreenAction.OnInternetStatus(it) }
}
