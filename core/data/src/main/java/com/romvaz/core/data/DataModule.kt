package com.romvaz.core.data

import com.romvaz.core.data.implementation.WebHookDataImplementation
import com.romvaz.core.domain.api.WebHookApi
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.datastore.DataStoreModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}

