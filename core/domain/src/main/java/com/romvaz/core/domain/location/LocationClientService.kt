package com.romvaz.core.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClientService {

    fun getLocationUpdates(interval: Long): Flow<Location>

    class LocationException(message: String) : Exception(message)
}
