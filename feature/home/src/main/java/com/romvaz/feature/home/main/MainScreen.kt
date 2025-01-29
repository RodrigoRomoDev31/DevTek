package com.romvaz.feature.home.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.DevTekScaffold
import com.romvaz.core.ui.components.DevTekTransparentHeader
import com.romvaz.core.ui.components.SnackBarTopComponent
import com.romvaz.core.ui.components.SnackBarTopStatus
import com.romvaz.core.ui.components.SnackBarVisuals
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.utils.GlobalUtils


@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state by viewModel.observe().collectAsStateWithLifecycle()

    Content(
        sendHelpState = state.sendHelp,
        errorInSendHelpState = state.errorInSendHelp,
        snackBarTopStatus = state.snackBarTopStatus,
        counterState = state.counter,
        sendHelpCallback = viewModel::sendHelp,
        navigateToUserCallback = viewModel::navigateToUserInfo
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    sendHelpState: Boolean,
    errorInSendHelpState: Throwable?,
    snackBarTopStatus: SnackBarTopStatus,
    counterState: Int,
    sendHelpCallback: () -> Unit,
    navigateToUserCallback: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(key1 = counterState) {
        if (counterState > 0)
            if (errorInSendHelpState != null)
                snackBarHostState.showSnackbar(
                    SnackBarVisuals(
                        message = context.getString(R.string.send_help_alert_failure),
                        withDismissAction = true
                    )
                )
            else if (sendHelpState)
                snackBarHostState.showSnackbar(
                    SnackBarVisuals(
                        message = context.getString(R.string.send_help_alert_success),
                        withDismissAction = true
                    )
                )
            else
                snackBarHostState.showSnackbar(
                    SnackBarVisuals(
                        message = context.getString(R.string.send_help_alert_failure),
                        withDismissAction = true
                    )
                )
    }

    DevTekScaffold(
        header = {
            DevTekTransparentHeader(
                icon = painterResource(R.drawable.ic_devtek_logo),
                iconTint = MaterialTheme.colorScheme.onSurface,
                primaryAction = {
                    IconButton(onClick = {
                        sendHelpCallback()
                    }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.devTekColors.notificationError
                        )
                    }
                },
                secondaryActions = {
                    IconButton(onClick = {
                        navigateToUserCallback()
                    }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.devTekColors.Primary100
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackBarTopComponent(
                hostState = snackBarHostState,
                snackBarTopStatus = snackBarTopStatus,
                onClickAction = { snackBarHostState.currentSnackbarData?.dismiss() },
                infoClickAction = { GlobalUtils.goToSettings(context) }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            Text("DevTek")
        }
    }
}
