package com.romvaz.devtek.di

import android.content.Context
import com.romvaz.core.data.DataModule
import com.romvaz.core.ui.navigation.ComposeNavigator
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.devtek.DevTekApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context): DevTekApplication {
        return app as DevTekApplication
    }

    @Provides
    @Singleton
    fun providesNavigator(): Navigator =
        ComposeNavigator()
}
