package com.romvaz.feature.user.info

import com.romvaz.core.domain.models.HardUserPreferenceModel

data class UserScreenUiState(
    val userPreferenceModel: HardUserPreferenceModel? = null
)
