package com.romvaz.devtek.di

import android.content.Context
import com.romvaz.core.data.DataModule
import com.romvaz.core.network.NetworkModule
import com.romvaz.core.ui.navigation.ComposeNavigator
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.devtek.DevTekApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Create Main dependency Injection Module and add Modules from data and network modules
@Module(
    includes = [
        DataModule::class,
        NetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    //Provides Application context to app
    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context): DevTekApplication {
        return app as DevTekApplication
    }

    // Provides Navigator class to project
    @Provides
    @Singleton
    fun providesNavigator(): Navigator =
        ComposeNavigator()
}
