package com.romvaz.core.data

import com.romvaz.datastore.DataStoreModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DataStoreModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule
