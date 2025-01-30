package com.romvaz.feature.home.main

import com.google.android.gms.maps.model.LatLng
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.ui.components.SnackBarTopStatus

data class MainScreenUiState(
    val userInfo: HardUserPreferenceModel? = null,
    val sendHelp: Boolean = false,
    val errorInSendHelp: Throwable? = null,
    val snackBarTopStatus: SnackBarTopStatus = SnackBarTopStatus.SUCCESS,
    val counter: Int = 0,
    val latLng: MutableList<LatLng> = mutableListOf()
)
