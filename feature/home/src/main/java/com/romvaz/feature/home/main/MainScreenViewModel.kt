package com.romvaz.feature.home.main

import androidx.lifecycle.ViewModel
import com.romvaz.core.domain.routes.UserRoute
import com.romvaz.core.ui.navigation.NavigationCommand
import com.romvaz.core.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun navigateToUserInfo() =
        navigator.navigate(NavigationCommand.NavigateTo(UserRoute.UserInfoRoute.route))

}
