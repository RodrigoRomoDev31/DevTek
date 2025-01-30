package com.romvaz.core.domain.datastore

import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import kotlinx.coroutines.flow.Flow

// This interface defines the contract for a service that manages user preferences.
interface UserPreferenceService {

    // This function retrieves the current user preferences as a Flow of `HardUserPreferenceModel`.
    // It returns a Flow so that it can be observed for changes in the user preferences.
    fun getPreferences(): Flow<HardUserPreferenceModel>

    // This function updates the user information with a new `HardUserPreferenceModel`.
    suspend fun updateUserInfo(userInfo: HardUserPreferenceModel)
}
