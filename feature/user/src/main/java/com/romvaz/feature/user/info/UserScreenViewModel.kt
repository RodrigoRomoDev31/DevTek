package com.romvaz.feature.user.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romvaz.core.store.Store
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.feature.user.info.middlewares.UserInfoMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// View Model for UserScreen
// Injects navigator for navigation and a middleware in charge of observe the HardUserPreference
@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    userInfoMiddleware: UserInfoMiddleware
) : ViewModel() {

    // Initiate store object
    private val store = Store(
        UserScreenUiState(),
        UserScreenReducer(),
        viewModelScope,
        listOf(
            userInfoMiddleware
        )
    )

    // Observe the UIState throw changes
    fun observeState(): StateFlow<UserScreenUiState> = store.observe()

    // Navigate back
    fun popBack() =
        navigator.navigate(NavigationCommand.PopBackstack)
}
