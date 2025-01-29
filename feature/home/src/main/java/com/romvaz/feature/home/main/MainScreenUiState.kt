package com.romvaz.feature.home.main

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.ui.components.SnackBarTopStatus

data class MainScreenUiState(
    val userInfo: HardUserPreferenceModel? = null,
    val sendHelp: Boolean = false,
    val errorInSendHelp: Throwable? = null,
    val snackBarTopStatus: SnackBarTopStatus = SnackBarTopStatus.SUCCESS,
    val counter: Int = 0
)
