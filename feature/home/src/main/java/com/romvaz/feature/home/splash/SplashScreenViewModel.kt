package com.romvaz.feature.home.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.core.domain.routes.LoginRoute
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.core.ui.utils.DELAY_TIME_2000
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(DELAY_TIME_2000)
            navigator.navigate(
                NavigationCommand.NavigateTo(
                    LoginRoute.UserLoginRoute.route,
                    NavOptions.Builder().setPopUpTo(HomeRoute.SplashRoute.route, true).build()
                )
            )
        }
    }
}
