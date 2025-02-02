package com.romvaz.core.data.implementation.location

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.romvaz.core.domain.location.LocationClientService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

/**
 * Implementation of LocationClientService that provides location updates using FusedLocationProviderClient.
 */
class LocationClientImplementation(
    private val client: FusedLocationProviderClient
) : LocationClientService {

    /**
     * Provides location updates at a specified interval using callbackFlow.
     * @param interval The interval at which location updates are requested (in milliseconds).
     * @return A Flow emitting Location objects as location updates are received.
     * Suppresses lint warning for missing location permission check, permission requested on Main Module
     * Create a LocationRequest with specified parameters
     * Iniciciate Location callback to receive location updates.
     * Request location updates from the FusedLocationProviderClient.
     * Clean up: remove location updates when the Flow is closed or canceled.
     * Stop location updates.
     */
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> =
        callbackFlow {

            val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(interval)
                .build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let {
                        launch { send(it) }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
}
