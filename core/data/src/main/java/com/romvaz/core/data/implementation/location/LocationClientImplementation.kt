package com.romvaz.core.data.implementation.location

import android.content.Context
import android.location.Location
import android.location.LocationManager
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

class LocationClientImplementation(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationClientService {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager


    override fun getLocationUpdates(interval: Long): Flow<Location> =
        callbackFlow {

            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGpsEnabled && !isNetworkEnabled) {
                throw LocationClientService.LocationException("Gps disbled")
            }

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