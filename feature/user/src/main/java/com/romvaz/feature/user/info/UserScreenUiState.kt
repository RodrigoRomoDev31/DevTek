package com.romvaz.feature.user.info

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel

// States to observe in User Screen
data class UserScreenUiState(
    val userPreferenceModel: HardUserPreferenceModel? = null
)
