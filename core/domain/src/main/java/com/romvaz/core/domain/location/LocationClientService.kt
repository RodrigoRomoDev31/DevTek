package com.romvaz.core.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

// This interface defines the contract for a service that provides location updates.
interface LocationClientService {

    // This function provides location updates at a specified interval.
    // It returns a Flow of `Location` objects which emit updated location data.
    // The `interval` parameter determines the frequency of the location updates.
    // The location updates will continue to be emitted until the Flow is collected or cancelled.
    fun getLocationUpdates(interval: Long): Flow<Location>
}
