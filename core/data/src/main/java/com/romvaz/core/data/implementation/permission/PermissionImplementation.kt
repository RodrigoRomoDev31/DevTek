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

    private val _permissionFlow = MutableStateFlow(isPermissionGranted())
    override val permissionFlow: StateFlow<Boolean> = _permissionFlow

    override fun updatePermissionState() {
        _permissionFlow.value = isPermissionGranted()
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}
