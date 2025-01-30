package com.romvaz.core.data.implementation.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.romvaz.core.domain.permissions.PermissionService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Implementation of PermissionService to manage and observe the state of location permission.
class PermissionImplementation(
    private val context: Context // Context is required to check permission status.
) : PermissionService {

    // MutableStateFlow to hold the current location permission status (granted or not).
    private val _locationPermissionFlow = MutableStateFlow(isPermissionGranted())

    // Exposed StateFlow to observe the current location permission status.
    // This is a read-only flow, only updates can be made via `updateLocationPermissionState`.
    override val locationPermissionFlow: StateFlow<Boolean> = _locationPermissionFlow

    // Updates the location permission state in the flow.
    override fun updateLocationPermissionState() {
        _locationPermissionFlow.value = isPermissionGranted() // Update state based on current permission status.
    }

    // Helper function to check if the location permission is granted.
    private fun isPermissionGranted(): Boolean {
        // Checks if the ACCESS_FINE_LOCATION permission is granted.
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}
