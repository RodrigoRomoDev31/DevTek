package com.romvaz.feature.home.main

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.domain.models.mock.MockLatLngModel
import com.romvaz.core.network.connectivity.InternetStatus
import com.romvaz.core.store.Reducer
import com.romvaz.core.ui.components.SnackBarTopStatus

class MainScreenReducer : Reducer<MainScreenUiState, MainScreenAction> {
    override fun reduce(state: MainScreenUiState, action: MainScreenAction): MainScreenUiState =
        when (action) {
            is MainScreenAction.OnUserInfoChange -> state.copy(userInfo = action.userPreferenceModel)
            is MainScreenAction.OnSendHelp -> state.copy(
                sendHelp = action.sendHelp,
                snackBarTopStatus = action.topStatus,
                counter = state.counter + 1
            )

            is MainScreenAction.OnErrorInSendHelp -> state.copy(
                errorInSendHelp = action.throwable,
                counter = state.counter + 1,
                snackBarTopStatus = SnackBarTopStatus.ERROR
            )

            is MainScreenAction.OnUserLocation -> state.mockData()
            is MainScreenAction.OnInternetStatus -> state.copy(
                internetState = action.status,
                snackBarTopStatus = SnackBarTopStatus.INTERNET
            )

            else -> state
        }

    private fun MainScreenUiState.mockData(): MainScreenUiState {
        val mockLocation = if (index < MockLatLngModel.mockListLatLng.size)
            MockLatLngModel.mockListLatLng[index]
        else
            MockLatLngModel.mockListLatLng.last()

        return this.copy(
            userLocationRoute = (userLocationRoute + mockLocation).toMutableList(),
            index = index + 1
        )
    }
}

sealed interface MainScreenAction {
    data class OnUserInfoChange(
        val userPreferenceModel: HardUserPreferenceModel
    ) : MainScreenAction

    data class OnSendHelp(
        val sendHelp: Boolean,
        val topStatus: SnackBarTopStatus
    ) : MainScreenAction

    data class OnErrorInSendHelp(
        val throwable: Throwable
    ) : MainScreenAction

    data class OnInternetStatus(
        val status: InternetStatus
    ) : MainScreenAction

    data object OnUserLocation : MainScreenAction

    data object SendHelp : MainScreenAction
}
