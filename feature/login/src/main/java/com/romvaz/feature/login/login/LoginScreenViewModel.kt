package com.romvaz.feature.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romvaz.core.store.Store
import com.romvaz.core.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val store = Store(
        LoginScreenStateUi(),
        LoginScreenReducer(),
        viewModelScope,
        listOf()
    )

    fun observeState(): StateFlow<LoginScreenStateUi> = store.observe()

    fun updateEmail(value: String) = store.dispatch(LoginScreenAction.OnEmailChange(value))

    fun updatePassword(value: String) = store.dispatch(LoginScreenAction.OnPasswordChange(value))
}
