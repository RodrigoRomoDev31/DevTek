package com.romvaz.feature.home.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romvaz.core.domain.routes.UserRoute
import com.romvaz.core.store.Store
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.feature.home.main.middlewares.GetUserLocationMiddleware
import com.romvaz.feature.home.main.middlewares.InternetServiceMiddleware
import com.romvaz.feature.home.main.middlewares.SendHelpMiddleware
import com.romvaz.feature.home.main.middlewares.UserInfoMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    sendHelpMiddleware: SendHelpMiddleware,
    userInfoMiddleware: UserInfoMiddleware,
    getUserLocationMiddleware: GetUserLocationMiddleware,
    internetServiceMiddleware: InternetServiceMiddleware
) : ViewModel() {

    private val store = Store(
        MainScreenUiState(),
        MainScreenReducer(),
        viewModelScope,
        listOf(
            sendHelpMiddleware,
            userInfoMiddleware,
            getUserLocationMiddleware,
            internetServiceMiddleware
        )
    )

    fun observe(): StateFlow<MainScreenUiState> = store.observe()

    fun navigateToUserInfo() =
        navigator.navigate(NavigationCommand.NavigateTo(UserRoute.UserInfoRoute.route))

    fun sendHelp() =
        store.dispatch(MainScreenAction.SendHelp)

}
