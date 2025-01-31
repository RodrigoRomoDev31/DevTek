package com.romvaz.feature.home.main

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.domain.models.mock.MockLatLngModel
import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.store.Reducer
import com.romvaz.core.ui.components.SnackBarTopStatus

/**
 * Reducer responsible for managing and updating the state of the Main Screen based on dispatched actions.
 * It processes different [MainScreenAction] events and updates the [MainScreenUiState] accordingly.
 */
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

            is MainScreenAction.OnNoInternetConnection ->
                state.copy(internetState = InternetStatus.UNAVAILABLE_CONNECTION)

            is MainScreenAction.OnSendHelpRequest -> state.copy(onSendHelpRequest = !state.onSendHelpRequest)

            is MainScreenAction.SendHelp -> state.copy(onSendHelpRequest = false)

            is MainScreenAction.UpdateProblem -> state.copy(problem = action.problem)
        }

    /**
     * Mock location data for the Main Screen state.
     * This function updates the user location route and increments the index for mocked data.
     *
     * @return The new state with updated user location data.
     */
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

/**
 * Represents actions that can trigger state changes in the Main Screen.
 */
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

    data object OnNoInternetConnection : MainScreenAction

    data object OnUserLocation : MainScreenAction

    data object SendHelp : MainScreenAction

    data object OnSendHelpRequest : MainScreenAction

    data class UpdateProblem(
        val problem: String
    ) : MainScreenAction
}
