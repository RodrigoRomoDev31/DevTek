package com.romvaz.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.romvaz.core.domain.models.HardUserPreferenceModel
import com.romvaz.datastore.implementations.AppPreferencesImplementation
import com.romvaz.datastore.services.AppPreferenceService
import com.romvaz.datastore.utils.AppPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun providesAppPreferences(
        @ApplicationContext context: Context
    ): DataStore<HardUserPreferenceModel> {
        return context.appPreferencesDataStore
    }

    @Provides
    @Singleton
    fun providesPreferenceService(
        dataStore: DataStore<HardUserPreferenceModel>
    ): AppPreferenceService =
        AppPreferencesImplementation(dataStore)
}

private val Context.appPreferencesDataStore: DataStore<HardUserPreferenceModel> by dataStore(
    fileName = "app_preferences.json",
    serializer = AppPreferencesSerializer
)
