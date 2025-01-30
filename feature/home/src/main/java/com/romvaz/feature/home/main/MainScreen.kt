package com.romvaz.feature.home.main

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.AlertComponentComponent
import com.romvaz.core.ui.components.DevTekScaffold
import com.romvaz.core.ui.components.DevTekTransparentHeader
import com.romvaz.core.ui.components.SnackBarTopComponent
import com.romvaz.core.ui.components.SnackBarTopStatus
import com.romvaz.core.ui.components.SnackBarVisuals
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.theme.isDarkTheme
import com.romvaz.core.ui.utils.DURATION_FOR_AUTO_FOCUS_MAPS
import com.romvaz.core.ui.utils.GlobalUtils
import com.romvaz.core.ui.utils.MAPS_ZOOM
import com.romvaz.core.ui.utils.START_POSITION_FOR_MAP


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val state by viewModel.observe().collectAsStateWithLifecycle()

    val locationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(key1 = locationPermissionState.status) {
        viewModel.updatePermissionState()
    }

    Content(
        sendHelpState = state.sendHelp,
        errorInSendHelpState = state.errorInSendHelp,
        snackBarTopStatus = state.snackBarTopStatus,
        counterState = state.counter,
        userLocationRouteState = state.userLocationRoute,
        internetState = state.internetState,
        locationPermissionState = locationPermissionState.status.isGranted,
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
    userLocationRouteState: MutableList<LatLng>,
    internetState: InternetStatus,
    locationPermissionState: Boolean,
    sendHelpCallback: () -> Unit,
    navigateToUserCallback: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position =
            CameraPosition.fromLatLngZoom(
                LatLng(
                    START_POSITION_FOR_MAP,
                    START_POSITION_FOR_MAP
                ), MAPS_ZOOM
            )
    }


    LaunchedEffect(key1 = userLocationRouteState) {
        if (userLocationRouteState.isNotEmpty() && internetState == InternetStatus.HAVE_CONNECTION)
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLng(userLocationRouteState.last()),
                durationMs = DURATION_FOR_AUTO_FOCUS_MAPS
            )

    }

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
        if (internetState == InternetStatus.UNAVAILABLE_CONNECTION
            || internetState == InternetStatus.LOST_CONNECTION
            || !locationPermissionState)

            AlertComponentComponent(
                modifier = Modifier.padding(paddingValues),
                locationProblem = !locationPermissionState
            )
        else
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                cameraPositionState = cameraPositionState
            ) {
                Polyline(
                    points = userLocationRouteState,
                    color = MaterialTheme.devTekColors.Primary100,
                    width = 8f
                )

                if (userLocationRouteState.isNotEmpty()) {
                    Marker(
                        state = MarkerState(userLocationRouteState.first()),
                        title = stringResource(R.string.route_start)
                    )
                    Marker(
                        state = MarkerState(userLocationRouteState.last()),
                        title = stringResource(R.string.current_position)
                    )
                }
            }
    }

    rememberSystemUiController().setStatusBarColor(
        color = Color.Transparent,
        darkIcons = !isDarkTheme()
    )
}
