package com.romvaz.feature.user.info

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.store.Reducer

/**
 * Reducer responsible for handling state changes in the User Screen.
 * It processes [UserScreenAction] events and updates the [UserScreenUiState] accordingly.
 */
class UserScreenReducer : Reducer<UserScreenUiState, UserScreenAction> {

    override fun reduce(state: UserScreenUiState, action: UserScreenAction): UserScreenUiState =
        when (action) {
            is UserScreenAction.OnUserInfoChange ->
                state.copy(userPreferenceModel = action.userPreferenceModel)
        }
}

sealed interface UserScreenAction {

    data class OnUserInfoChange(
        val userPreferenceModel: HardUserPreferenceModel
    ) : UserScreenAction
}
