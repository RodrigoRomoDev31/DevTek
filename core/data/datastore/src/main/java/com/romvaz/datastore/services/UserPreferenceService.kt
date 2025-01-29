package com.romvaz.datastore.services

import com.romvaz.core.domain.models.HardUserPreferenceModel
import kotlinx.coroutines.flow.Flow

interface UserPreferenceService {
    fun getPreferences(): Flow<HardUserPreferenceModel>

    suspend fun updateUserInfo(userInfo: HardUserPreferenceModel)
}
