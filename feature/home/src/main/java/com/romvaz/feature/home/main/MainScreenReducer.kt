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
            // Update user information in the state
            is MainScreenAction.OnUserInfoChange -> state.copy(userInfo = action.userPreferenceModel)

            // Update send help status and snackbar state, and increment counter
            is MainScreenAction.OnSendHelp -> state.copy(
                sendHelp = action.sendHelp,
                snackBarTopStatus = action.topStatus,
                counter = state.counter + 1
            )

            // Update the error in sending help, snackbar status, and increment counter
            is MainScreenAction.OnErrorInSendHelp -> state.copy(
                errorInSendHelp = action.throwable,
                counter = state.counter + 1,
                snackBarTopStatus = SnackBarTopStatus.ERROR
            )

            // Mock user location data and return updated state
            is MainScreenAction.OnUserLocation -> state.mockData()

            // Update internet status and snackbar state
            is MainScreenAction.OnInternetStatus -> state.copy(
                internetState = action.status,
                snackBarTopStatus = SnackBarTopStatus.INTERNET
            )

            // Handle no internet connection scenario
            is MainScreenAction.OnNoInternetConnection ->
                state.copy(internetState = InternetStatus.UNAVAILABLE_CONNECTION)

            // Update onSendHelpRequest status
            is MainScreenAction.OnSendHelpRequest -> state.copy(onSendHelpRequest = !state.onSendHelpRequest)

            // Dispatch Action to send help and update onSendHelpRequest status
            is MainScreenAction.SendHelp -> state.copy(onSendHelpRequest = false)

            // Update Problem status
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

    /**
     * Action triggered when user information changes.
     *
     * @param userPreferenceModel The updated user preference model.
     */
    data class OnUserInfoChange(
        val userPreferenceModel: HardUserPreferenceModel
    ) : MainScreenAction

    /**
     * Action triggered when the send help button is pressed.
     *
     * @param sendHelp The status of the send help action (true/false).
     * @param topStatus The current snackbar status.
     */
    data class OnSendHelp(
        val sendHelp: Boolean,
        val topStatus: SnackBarTopStatus
    ) : MainScreenAction

    /**
     * Action triggered when there's an error in sending help.
     *
     * @param throwable The exception that occurred during the process.
     */
    data class OnErrorInSendHelp(
        val throwable: Throwable
    ) : MainScreenAction

    /**
     * Action triggered when the internet connection status changes.
     *
     * @param status The current internet connection status.
     */
    data class OnInternetStatus(
        val status: InternetStatus
    ) : MainScreenAction

    /**
     * Action triggered when there is no internet connection.
     */
    data object OnNoInternetConnection : MainScreenAction

    /**
     * Action triggered when the user location needs to be updated.
     */
    data object OnUserLocation : MainScreenAction

    /**
     * Action triggered to send help.
     */
    data object SendHelp : MainScreenAction

    /**
     * Action triggered to request Help
     */
    data object OnSendHelpRequest : MainScreenAction

    /**
     * Action triggered to update problem
     */
    data class UpdateProblem(
        val problem: String
    ) : MainScreenAction
}
