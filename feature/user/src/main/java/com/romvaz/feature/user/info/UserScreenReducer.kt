package com.romvaz.feature.user.info

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.store.Reducer

class UserScreenReducer : Reducer<UserScreenUiState, UserScreenAction> {
    override fun reduce(state: UserScreenUiState, action: UserScreenAction): UserScreenUiState =
        when (action) {
            is UserScreenAction.OnUserInfoChange -> state.copy(userPreferenceModel = action.userPreferenceModel)
        }
}

sealed interface UserScreenAction {
    data class OnUserInfoChange(
        val userPreferenceModel: HardUserPreferenceModel
    ) : UserScreenAction
}
