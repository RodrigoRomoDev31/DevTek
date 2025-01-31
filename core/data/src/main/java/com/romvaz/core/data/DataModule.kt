package com.romvaz.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.google.android.gms.location.LocationServices
import com.romvaz.core.data.implementation.api.WebHookDataImplementation
import com.romvaz.core.data.implementation.datastore.UserPreferencesImplementation
import com.romvaz.core.data.implementation.location.LocationClientImplementation
import com.romvaz.core.data.implementation.network.InternetStatusImplementation
import com.romvaz.core.data.implementation.permission.PermissionImplementation
import com.romvaz.core.data.utils.AppPreferencesSerializer
import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.datastore.UserPreferenceService
import com.romvaz.core.domain.location.LocationClientService
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.core.domain.permissions.PermissionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// DataModule for Dependency Injection using Dagger-Hilt
// This module provides various services like WebHook, Location, Internet Status, Permission,
// and User Preferences to the application using Dagger-Hilt annotations.

// The module is installed in the SingletonComponent, which means the dependencies provided here
// will have a singleton scope (one instance per application).
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesWebHookService(
        webHookApi: WebHookApi
    ): WebHookDataService =
        WebHookDataImplementation(webHookApi)

    @Provides
    @Singleton
    fun providesLocationClientService(
        @ApplicationContext context: Context
    ): LocationClientService =
        LocationClientImplementation(
            LocationServices.getFusedLocationProviderClient(context)
        )

    @Provides
    @Singleton
    fun providesInternetStatus(@ApplicationContext context: Context): InternetStatusService =
        InternetStatusImplementation(context)

    @Provides
    @Singleton
    fun providesPermissionService(@ApplicationContext context: Context): PermissionService =
        PermissionImplementation(context)

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
    ): UserPreferenceService =
        UserPreferencesImplementation(dataStore)
}


// Extension to create the DataStore instance for user preferences.
private val Context.appPreferencesDataStore: DataStore<HardUserPreferenceModel> by dataStore(
    fileName = "app_preferences.json",  // DataStore file name to store preferences
    serializer = AppPreferencesSerializer  // Serializer to convert HardUserPreferenceModel to and from JSON
)

