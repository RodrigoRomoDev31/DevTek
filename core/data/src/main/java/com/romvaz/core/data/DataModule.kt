package com.romvaz.core.data

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.romvaz.core.data.implementation.api.WebHookDataImplementation
import com.romvaz.core.data.implementation.location.LocationClientImplementation
import com.romvaz.core.data.implementation.network.InternetStatusImplementation
import com.romvaz.core.data.implementation.permission.PermissionImplementation
import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.location.LocationClientService
import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.core.domain.permissions.PermissionService
import com.romvaz.datastore.DataStoreModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Data Main Module for dependency injection
// Includes DataStoreModule
@Module(
    includes = [
        DataStoreModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule {

    //Provides Webhook Service to App
    @Provides
    @Singleton
    fun providesWebHookService(
        webHookApi: WebHookApi
    ): WebHookDataService =
        WebHookDataImplementation(webHookApi)

    // Provides Location Client Service To App
    @Provides
    @Singleton
    fun providesLocationClientService(
        @ApplicationContext context: Context
    ): LocationClientService =
        LocationClientImplementation(
            LocationServices.getFusedLocationProviderClient(context)
        )

    // Provides Internet Status Service to App
    @Provides
    @Singleton
    fun providesInternetStatus(@ApplicationContext context: Context): InternetStatusService =
        InternetStatusImplementation(context)

    //Provides Permission State Service to App
    @Provides
    @Singleton
    fun providesPermissionService(@ApplicationContext context: Context) : PermissionService =
        PermissionImplementation(context)
}

