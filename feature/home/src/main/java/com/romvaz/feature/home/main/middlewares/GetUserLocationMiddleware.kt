package com.romvaz.feature.home.main.middlewares

import android.util.Log
import com.romvaz.core.domain.location.LocationClientService
import com.romvaz.core.domain.permissions.PermissionService
import com.romvaz.core.store.StateSideEffect
import com.romvaz.core.ui.utils.DELAY_TIME_1000
import com.romvaz.feature.home.main.MainScreenAction
import com.romvaz.feature.home.main.MainScreenUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserLocationMiddleware @Inject constructor(
    private val locationClientService: LocationClientService,
    private val permissionService: PermissionService
) : StateSideEffect<MainScreenUiState, MainScreenAction>() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observe(stateFlow: Flow<MainScreenUiState>): Flow<MainScreenAction> =
        permissionService.permissionFlow
            .flatMapLatest { granted ->
                Log.d("hola", granted.toString())
                if (granted) {
                    locationClientService
                        .getLocationUpdates(DELAY_TIME_1000)
                        .distinctUntilChanged()
                        .map {
                            MainScreenAction.OnUserLocation
                        }
                } else
                    emptyFlow()
            }
}
