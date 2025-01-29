package com.romvaz.datastore.implementations

import androidx.datastore.core.DataStore
import com.romvaz.core.domain.models.HardUserPreferenceModel
import com.romvaz.datastore.services.AppPreferenceService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AppPreferencesImplementation(
    private val dataStore: DataStore<HardUserPreferenceModel>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppPreferenceService {

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
