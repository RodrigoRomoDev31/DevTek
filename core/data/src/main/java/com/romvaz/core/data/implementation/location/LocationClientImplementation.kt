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

// Implementation of LocationClientService that provides location updates using FusedLocationProviderClient.
class LocationClientImplementation(
    private val client: FusedLocationProviderClient
) : LocationClientService {

    // Provides location updates at a specified interval using callbackFlow.
    // @param interval The interval at which location updates are requested (in milliseconds).
    // @return A Flow emitting Location objects as location updates are received.
    // Suppresses lint warning for missing location permission check, permission requested on Main Module
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> =
        callbackFlow {
            // Create a LocationRequest with specified parameters
            val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
                .setWaitForAccurateLocation(false) // No need to wait for an accurate location
                .setMinUpdateIntervalMillis(interval) // Set minimum update interval to the specified interval
                .build()

            // Location callback to receive location updates.
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    // If locations are available, send the latest location through the Flow.
                    result.locations.lastOrNull()?.let {
                        launch { send(it) } // Send location to the Flow.
                    }
                }
            }

            // Request location updates from the FusedLocationProviderClient.
            client.requestLocationUpdates(
                request, // Location request with interval and accuracy.
                locationCallback, // Callback to handle location results.
                Looper.getMainLooper() // Use main looper for the callback.
            )

            // Clean up: remove location updates when the Flow is closed or canceled.
            awaitClose {
                client.removeLocationUpdates(locationCallback) // Stop location updates.
            }
        }
}
