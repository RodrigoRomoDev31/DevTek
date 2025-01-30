package com.romvaz.feature.user.info

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.store.Reducer

/**
 * Reducer responsible for handling state changes in the User Screen.
 * It processes [UserScreenAction] events and updates the [UserScreenUiState] accordingly.
 */
class UserScreenReducer : Reducer<UserScreenUiState, UserScreenAction> {

    /**
     * Reduces the current state based on the received action and returns the updated state.
     */
    override fun reduce(state: UserScreenUiState, action: UserScreenAction): UserScreenUiState =
        when (action) {
            is UserScreenAction.OnUserInfoChange ->
                state.copy(userPreferenceModel = action.userPreferenceModel)
        }
}

/**
 * Represents user actions that can trigger state changes in the User Screen.
 */
sealed interface UserScreenAction {

    /**
     * Action triggered when user information changes.
     */
    data class OnUserInfoChange(
        val userPreferenceModel: HardUserPreferenceModel
    ) : UserScreenAction
}
