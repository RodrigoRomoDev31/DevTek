package com.romvaz.core.domain.permissions

import kotlinx.coroutines.flow.StateFlow

interface PermissionService {
    val locationPermissionFlow: StateFlow<Boolean>
    fun updateLocationPermissionState()
}
