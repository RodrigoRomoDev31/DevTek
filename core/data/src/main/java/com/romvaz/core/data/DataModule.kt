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

    // Provides WebHookDataService to the application by wrapping the WebHookApi in a WebHookDataImplementation.
    // This service is used for making network calls related to WebHook.
    @Provides
    @Singleton
    fun providesWebHookService(
        webHookApi: WebHookApi  // WebHookApi will be provided by Hilt
    ): WebHookDataService =
        WebHookDataImplementation(webHookApi)

    // Provides LocationClientService to the application.
    // It wraps the FusedLocationProviderClient into a LocationClientImplementation, which handles location updates.
    @Provides
    @Singleton
    fun providesLocationClientService(
        @ApplicationContext context: Context  // Injects the application context
    ): LocationClientService =
        LocationClientImplementation(
            LocationServices.getFusedLocationProviderClient(context)  // Obtains the FusedLocationProviderClient
        )

    // Provides InternetStatusService to the application.
    // This service checks for internet connectivity and listens for changes in the network status.
    @Provides
    @Singleton
    fun providesInternetStatus(@ApplicationContext context: Context): InternetStatusService =
        InternetStatusImplementation(context)

    // Provides PermissionService to the application.
    // It is used to check and manage permission states like ACCESS_FINE_LOCATION.
    @Provides
    @Singleton
    fun providesPermissionService(@ApplicationContext context: Context): PermissionService =
        PermissionImplementation(context)

    // Provides DataStore<HardUserPreferenceModel> for storing user preferences in the application.
    @Provides
    @Singleton
    fun providesAppPreferences(
        @ApplicationContext context: Context  // Injects the application context
    ): DataStore<HardUserPreferenceModel> {
        return context.appPreferencesDataStore  // Using the extension to get the DataStore instance
    }

    // Provides UserPreferenceService to the application,
    // which is a wrapper around DataStore to manage user preferences.
    @Provides
    @Singleton
    fun providesPreferenceService(
        dataStore: DataStore<HardUserPreferenceModel>  // Injects DataStore<HardUserPreferenceModel>
    ): UserPreferenceService =
        UserPreferencesImplementation(dataStore)
}

// Extension to create the DataStore instance for user preferences.
private val Context.appPreferencesDataStore: DataStore<HardUserPreferenceModel> by dataStore(
    fileName = "app_preferences.json",  // DataStore file name to store preferences
    serializer = AppPreferencesSerializer  // Serializer to convert HardUserPreferenceModel to and from JSON
)

