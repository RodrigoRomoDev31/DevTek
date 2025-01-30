package com.romvaz.core.data.implementation.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.romvaz.core.domain.permissions.PermissionService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PermissionImplementation(
    private val context: Context
): PermissionService {

    private val _locationPermissionFlow = MutableStateFlow(isPermissionGranted())
    override val locationPermissionFlow: StateFlow<Boolean> = _locationPermissionFlow

    override fun updateLocationPermissionState() {
        _locationPermissionFlow.value = isPermissionGranted()
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}
