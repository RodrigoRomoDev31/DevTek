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

@HiltViewModel
class UserScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    userInfoMiddleware: UserInfoMiddleware
) : ViewModel() {

    private val store = Store(
        UserScreenUiState(),
        UserScreenReducer(),
        viewModelScope,
        listOf(
            userInfoMiddleware
        )
    )

    fun observeState(): StateFlow<UserScreenUiState> = store.observe()

    fun popBack() =
        navigator.navigate(NavigationCommand.PopBackstack)
}
