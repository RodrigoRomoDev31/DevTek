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

/**
 * @param navigator The [Navigator] responsible for handling navigation commands and transitions within the app.
 * @param internetStatusService The service responsible for monitoring and managing the internet connection status.
 * @param permissionService The service responsible for handling and managing user permissions.
 * @param mainMiddlewares The middleware components that handle core business logic, such as API calls
 * or state management.
 */
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

    /**
     * Obtain if the connection id available
     * If not, dispatch a MainAction to update InternetState
     */
    init {
        viewModelScope.launch {
            if (!internetStatusService.theresInternet())
                store.dispatch(MainScreenAction.OnNoInternetConnection)
        }
    }

    fun observe(): StateFlow<MainScreenUiState> = store.observe()

    /**
     * Navigates to the User Info screen by issuing a NavigateTo command.
     */
    fun navigateToUserInfo() =
        navigator.navigate(NavigationCommand.NavigateTo(UserRoute.UserInfoRoute.route))

    /**
     * Dispatches an action to the store to trigger the "SendHelpRequest" functionality.
     */
    fun sendHelpRequest() =
        store.dispatch(MainScreenAction.OnSendHelpRequest)

    /**
     * Dispatches an action to the store to trigger the "SendHelp" functionality.
     */
    fun sendHelp() =
        store.dispatch(MainScreenAction.SendHelp)

    /**
     * Updates the current state of the location permission by invoking the updateLocationPermissionState
     */
    fun updatePermissionState() =
        permissionService.updateLocationPermissionState()

}
