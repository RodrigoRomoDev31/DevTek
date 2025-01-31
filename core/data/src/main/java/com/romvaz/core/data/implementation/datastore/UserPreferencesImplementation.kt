package com.romvaz.core.data.implementation.datastore

import androidx.datastore.core.DataStore
import com.romvaz.core.domain.datastore.UserPreferenceService
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Implementation of UserPreferenceService that interacts with a DataStore to persist user preferences.
 * Function getPreferences retrieve the current preferences as a Flow.
 * Returns a Flow of HardUserPreferenceModel that emits the latest saved preferences.
 * Function updateUserInfo update the user information in the DataStore.
 * The userInfo parameter represents the new user preferences to save.
 */
class UserPreferencesImplementation(
    private val dataStore: DataStore<HardUserPreferenceModel>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserPreferenceService {

    override fun getPreferences(): Flow<HardUserPreferenceModel> =
        dataStore.data

    override suspend fun updateUserInfo(userInfo: HardUserPreferenceModel) {
        withContext(dispatcher) {
            dataStore.updateData {
                userInfo
            }
        }
    }
}
