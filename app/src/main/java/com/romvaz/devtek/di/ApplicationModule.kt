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

// Main DI module for the application.
// This module includes dependencies from the DataModule and NetworkModule
// and provides essential application-level services like the application context and navigator.

@Module(
    includes = [
        DataModule::class,    // Includes DataModule which provides services related to data storage and API calls.
        NetworkModule::class  // Includes NetworkModule which provides services related to network and API requests.
    ]
)
// The module is installed in SingletonComponent, meaning its dependencies will have a singleton scope across the app.
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    // Provides the Application context to the app.
    // This makes the application context available for dependency injection throughout the app.
    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context): DevTekApplication {
        return app as DevTekApplication  // Casts the app context to the custom application class (DevTekApplication).
    }

    // Provides the Navigator class to the app.
    // The Navigator is responsible for handling navigation logic in the app
    @Provides
    @Singleton
    fun providesNavigator(): Navigator =
        ComposeNavigator()  // Provides a single instance of ComposeNavigator as the navigator for the app.
}
