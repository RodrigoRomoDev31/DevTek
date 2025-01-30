package com.romvaz.feature.home.main.middlewares

import com.romvaz.core.domain.location.LocationClientService
import com.romvaz.core.store.StateSideEffect
import com.romvaz.core.ui.utils.DELAY_TIME_1000
import com.romvaz.feature.home.main.MainScreenAction
import com.romvaz.feature.home.main.MainScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserLocationMiddleware @Inject constructor(
    private val locationClientService: LocationClientService
) : StateSideEffect<MainScreenUiState, MainScreenAction>() {
    override fun observe(stateFlow: Flow<MainScreenUiState>): Flow<MainScreenAction> =
        locationClientService
            .getLocationUpdates(DELAY_TIME_1000)
            .distinctUntilChanged()
            .map {
                MainScreenAction.OnUserLocation
            }

}
