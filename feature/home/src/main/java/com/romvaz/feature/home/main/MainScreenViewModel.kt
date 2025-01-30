package com.romvaz.feature.home.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.core.domain.permissions.PermissionService
import com.romvaz.core.domain.routes.UserRoute
import com.romvaz.core.store.Store
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.feature.home.main.middlewares.MainMiddlewares
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val internetStatusService: InternetStatusService,
    private val permissionService: PermissionService,
    mainMiddlewares: MainMiddlewares
) : ViewModel() {

    private val store = Store(
        MainScreenUiState(),
        MainScreenReducer(),
        viewModelScope,
        listOf(
            mainMiddlewares.userInfoMiddleware,
            mainMiddlewares.sendHelpMiddleware,
            mainMiddlewares.internetServiceMiddleware,
            mainMiddlewares.getUserLocationMiddleware
        )
    )

    init {
        viewModelScope.launch {
            if (!internetStatusService.theresInternet())
                store.dispatch(MainScreenAction.OnNoInternetConnection)
        }
    }

    fun observe(): StateFlow<MainScreenUiState> = store.observe()

    fun navigateToUserInfo() =
        navigator.navigate(NavigationCommand.NavigateTo(UserRoute.UserInfoRoute.route))

    fun sendHelp() =
        store.dispatch(MainScreenAction.SendHelp)

    fun updatePermissionState() =
        permissionService.updateLocationPermissionState()

}
