package com.romvaz.feature.home.main.middlewares

import com.romvaz.core.domain.datastore.UserPreferenceService
import com.romvaz.core.store.StateSideEffect
import com.romvaz.feature.home.main.MainScreenAction
import com.romvaz.feature.home.main.MainScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Middleware to observe changes in HardUserPreference
 * Triggers an MainAction Every time it changes
 */
class UserInfoMiddleware @Inject constructor(
    private val userPreferenceService: UserPreferenceService
) : StateSideEffect<MainScreenUiState, MainScreenAction>() {
    override fun observe(stateFlow: Flow<MainScreenUiState>): Flow<MainScreenAction> =
        userPreferenceService
            .getPreferences()
            .distinctUntilChanged()
            .map { MainScreenAction.OnUserInfoChange(it) }

}
