package com.romvaz.core.data

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.romvaz.core.data.implementation.api.WebHookDataImplementation
import com.romvaz.core.data.implementation.location.LocationClientImplementation
import com.romvaz.core.data.implementation.network.InternetStatusImplementation
import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.location.LocationClientService
import com.romvaz.core.domain.network.InternetStatusService
import com.romvaz.datastore.DataStoreModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DataStoreModule::class
    ]
)
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
}

