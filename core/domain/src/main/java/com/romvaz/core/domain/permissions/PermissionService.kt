package com.romvaz.core.domain.permissions

import kotlinx.coroutines.flow.StateFlow

// This interface defines the contract for managing location permissions in the app.
interface PermissionService {

    // A StateFlow that holds the current status of the location permission.
    // It emits a Boolean value where:
    //   - `true` means the location permission is granted.
    //   - `false` means the location permission is denied.
    val locationPermissionFlow: StateFlow<Boolean>

    // This method is used to update the location permission state
    fun updateLocationPermissionState()
}
