package com.romvaz.feature.home.main

import com.google.android.gms.maps.model.LatLng
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.domain.models.network.InternetStatus
import com.romvaz.core.ui.components.SnackBarTopStatus

// UI State For MainScreen
data class MainScreenUiState(
    val userInfo: HardUserPreferenceModel? = null,
    val sendHelp: Boolean = false,
    val errorInSendHelp: Throwable? = null,
    val snackBarTopStatus: SnackBarTopStatus = SnackBarTopStatus.SUCCESS,
    val counter: Int = 0,
    val userLocationRoute: MutableList<LatLng> = mutableListOf(),
    val index: Int = 0,
    val internetState: InternetStatus = InternetStatus.HAVE_CONNECTION
)
