package com.romvaz.core.network

import android.content.Context
import com.romvaz.core.network.connectivity.InternetStatusInterface
import com.romvaz.core.network.connectivity.InternetStatusInterfaceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesInternetStatus(@ApplicationContext context: Context): InternetStatusInterface =
        InternetStatusInterfaceService(context)
}
