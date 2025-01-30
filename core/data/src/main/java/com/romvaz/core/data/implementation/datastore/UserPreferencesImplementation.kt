package com.romvaz.core.data.implementation.datastore

import androidx.datastore.core.DataStore
import com.romvaz.core.domain.datastore.UserPreferenceService
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

// Implementation of UserPreferenceService that interacts with a DataStore to persist user preferences.
class UserPreferencesImplementation(
    private val dataStore: DataStore<HardUserPreferenceModel>,  // The DataStore to read and write user preferences
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO  // The dispatcher for IO-bound tasks
) : UserPreferenceService {

    // Function to retrieve the current preferences as a Flow.
    // Returns a Flow of HardUserPreferenceModel that emits the latest saved preferences.
    override fun getPreferences(): Flow<HardUserPreferenceModel> =
        dataStore.data  // Exposes the DataStore's data flow to get live updates on preferences.

    // Function to update the user information in the DataStore.
    // The userInfo parameter represents the new user preferences to save.
    override suspend fun updateUserInfo(userInfo: HardUserPreferenceModel) {
        withContext(dispatcher) {
            // Updates the DataStore with the new user information.
            dataStore.updateData {
                userInfo  // Replace the current data with the new user information.
            }
        }
    }
}
