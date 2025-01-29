package com.romvaz.feature.home.main

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
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

            else -> state
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

    data object SendHelp : MainScreenAction
}
