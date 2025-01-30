package com.romvaz.feature.user.info.middlewares

import com.romvaz.core.domain.datastore.UserPreferenceService
import com.romvaz.core.store.StateSideEffect
import com.romvaz.feature.user.info.UserScreenAction
import com.romvaz.feature.user.info.UserScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Middleware to observe changes in HardUserPreference
 * Triggers an UserScreenAction Every time it changes
 */
class UserInfoMiddleware @Inject constructor(
    private val userPreferenceService: UserPreferenceService
) : StateSideEffect<UserScreenUiState, UserScreenAction>() {
    override fun observe(stateFlow: Flow<UserScreenUiState>): Flow<UserScreenAction> =
        userPreferenceService
            .getPreferences()
            .distinctUntilChanged()
            .map { UserScreenAction.OnUserInfoChange(it) }

}
