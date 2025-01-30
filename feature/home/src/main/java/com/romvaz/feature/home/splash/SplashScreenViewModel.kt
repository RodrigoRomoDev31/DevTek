package com.romvaz.feature.home.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.romvaz.core.domain.datastore.UserPreferenceService
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.core.domain.routes.LoginRoute
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.core.ui.utils.DELAY_TIME_2000
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @param navigator The [Navigator] responsible for managing navigation actions within the app.
 * @param userPreferenceService The service used to manage and persist user preferences.
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val userPreferenceService: UserPreferenceService
) : ViewModel() {

    // Verify user information saved
    // If theres no user, navigate to LoginScreen else to MainScreen
    init {
        viewModelScope.launch {
            userPreferenceService.getPreferences().collect { userInfo ->
                delay(DELAY_TIME_2000)
                if (userInfo.email.isEmpty())
                    navigateTo(LoginRoute.UserLoginRoute.route)
                else
                    navigateTo(HomeRoute.MainRoute.route)
            }
        }
    }

    // Navigation To route in param
    private fun navigateTo(route: String) =
        navigator.navigate(
            NavigationCommand.NavigateTo(
                route,
                NavOptions.Builder().setPopUpTo(HomeRoute.SplashRoute.route, true)
                    .build()
            )
        )
}
