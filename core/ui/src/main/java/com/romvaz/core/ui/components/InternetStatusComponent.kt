package com.romvaz.core.ui.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.romvaz.core.network.connectivity.InternetStatus
import com.romvaz.core.ui.R

@Composable
fun InternetStatusComponent(
    internetStatus: InternetStatus,
    snackBarHostState: SnackbarHostState,
    haveConnectionCallback: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = internetStatus) {
        when (internetStatus) {
            InternetStatus.HAVE_CONNECTION -> haveConnectionCallback()
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
}
