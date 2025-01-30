package com.romvaz.core.domain.datastore

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import kotlinx.coroutines.flow.Flow

interface UserPreferenceService {
    fun getPreferences(): Flow<HardUserPreferenceModel>

    suspend fun updateUserInfo(userInfo: HardUserPreferenceModel)
}
