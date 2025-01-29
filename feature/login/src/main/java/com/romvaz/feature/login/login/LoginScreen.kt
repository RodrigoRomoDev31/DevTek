package com.romvaz.feature.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.romvaz.core.network.connectivity.InternetStatus
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.ButtonComponent
import com.romvaz.core.ui.components.DevTekScaffold
import com.romvaz.core.ui.components.LoadingComponent
import com.romvaz.core.ui.components.SnackBarTopComponent
import com.romvaz.core.ui.components.SnackBarTopStatus
import com.romvaz.core.ui.components.SnackBarVisuals
import com.romvaz.core.ui.components.VerticalSpacer
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.h3
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.theme.isDarkTheme
import com.romvaz.core.ui.utils.GlobalUtils
import com.romvaz.feature.login.components.InputFieldsComponent

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val state by viewModel.observeState().collectAsStateWithLifecycle()

    Content(
        emailState = state.emailInput,
        passwordState = state.password,
        loadingState = state.loading,
        btnEnableState = state.btnEnable,
        internetState = state.internetStatus,
        updateEmail = { viewModel.updateEmail(it) },
        updatePassword = { viewModel.updatePassword(it) },
        loginHardUser = viewModel::loginUser
    )
}

@Composable
private fun Content(
    emailState: String,
    passwordState: String,
    loadingState: Boolean,
    btnEnableState: Boolean,
    internetState: InternetStatus,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    loginHardUser: () -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(key1 = internetState) {
        when (internetState) {
            InternetStatus.UNAVAILABLE_CONNECTION ->
                snackBarHostState.showSnackbar(
                    SnackBarVisuals(
                        message = context.getString(R.string.no_internet_connection),
                        withDismissAction = false
                    )
                )

            InternetStatus.LOST_CONNECTION ->
                snackBarHostState.showSnackbar(
                    SnackBarVisuals(
                        message = context.getString(R.string.lost_conection_snackbar_title),
                        withDismissAction = false
                    )
                )

            else -> {}
        }
    }

    if (loadingState)
        LoadingComponent()

    DevTekScaffold(
        containerColor = if (isDarkTheme()) MaterialTheme.devTekColors.Gray60
        else MaterialTheme.devTekColors.Gray10,
        snackbarHost = {
            SnackBarTopComponent(
                hostState = snackBarHostState,
                snackBarTopStatus = SnackBarTopStatus.INTERNET,
                onClickAction = { snackBarHostState.currentSnackbarData?.dismiss() },
                infoClickAction = { GlobalUtils.goToSettings(context) }
            )
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Spacings.six)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(Spacings.three)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_devtek_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .padding(top = Spacings.eight),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.h3.copy(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.padding(vertical = Spacings.eight)
                )

                InputFieldsComponent(
                    value = emailState,
                    passWord = false,
                    onValueChange = { updateEmail(it) }
                )

                VerticalSpacer(height = Spacings.eight)

                InputFieldsComponent(
                    value = passwordState,
                    passWord = true,
                    onValueChange = { updatePassword(it) }
                )

                VerticalSpacer(height = Spacings.fourteen)

                ButtonComponent(
                    onClick = {
                        loginHardUser()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spacings.six),
                    enabled = btnEnableState
                ) {

                    Text(text = stringResource(id = R.string.login_action))

                }
            }

        }
    }

    rememberSystemUiController().setStatusBarColor(
        color = Color.Transparent,
        darkIcons = !isDarkTheme()
    )
}
