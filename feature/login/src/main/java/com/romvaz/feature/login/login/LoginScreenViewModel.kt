package com.romvaz.feature.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.core.domain.routes.LoginRoute
import com.romvaz.core.store.Store
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.core.ui.utils.DELAY_TIME_2000
import com.romvaz.feature.login.login.middlewares.InternetServiceMiddleware
import com.romvaz.feature.login.login.middlewares.LoginMiddleware
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @param navigator The [Navigator] responsible for managing navigation commands.
 * @param internetStatusService The service used to manage and monitor internet connection status.
 * @param loginMiddleware The middleware responsible for handling login-related logic.
 * @param internetServiceMiddleware The middleware responsible for handling internet connection-related logic.
 */
@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val internetStatusService: InternetStatusService,
    loginMiddleware: LoginMiddleware,
    internetServiceMiddleware: InternetServiceMiddleware
) : ViewModel() {

    private val store = Store(
        LoginScreenStateUi(),
        LoginScreenReducer(),
        viewModelScope,
        listOf(loginMiddleware, internetServiceMiddleware)
    )

    fun observeState(): StateFlow<LoginScreenStateUi> = store.observe()

    init {
        viewModelScope.launch {
            if (!internetStatusService.theresInternet())
                store.dispatch(LoginScreenAction.OnInternetStatus(InternetStatus.UNAVAILABLE_CONNECTION))
        }
    }

    fun updateEmail(value: String) = store.dispatch(LoginScreenAction.OnEmailChange(value))

    fun updatePassword(value: String) = store.dispatch(LoginScreenAction.OnPasswordChange(value))

    fun loginUser() = viewModelScope.launch {
        store.dispatch(LoginScreenAction.LoginHardUser)
        delay(DELAY_TIME_2000)
        navigator.navigate(
            NavigationCommand.NavigateTo(
                HomeRoute.MainRoute.route,
                NavOptions.Builder().setPopUpTo(LoginRoute.UserLoginRoute.route, true).build()
            )
        )
    }
}
