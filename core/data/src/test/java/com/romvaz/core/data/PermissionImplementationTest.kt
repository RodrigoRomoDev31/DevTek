package com.romvaz.core.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.romvaz.core.data.implementation.permission.PermissionImplementation
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PermissionImplementationTest {

    private lateinit var permissionImplementation: PermissionImplementation
    private val mockContext: Context = mockk()

    @Before
    fun setUp() {
        // Default behavior: Assume permission is granted
        mockPermissionStatus(PackageManager.PERMISSION_GRANTED)
        permissionImplementation = PermissionImplementation(mockContext)
    }

    private fun mockPermissionStatus(status: Int) {
        mockkStatic(ContextCompat::class) // Mock static function
        every {
            ContextCompat.checkSelfPermission(
                mockContext, Manifest.permission.ACCESS_FINE_LOCATION
            )
        } returns status
    }

    @Test
    fun `locationPermissionFlow should emit true when permission is granted`() = runTest {
        // Arrange: Simulate permission is granted
        mockPermissionStatus(PackageManager.PERMISSION_GRANTED)

        // Act: Collect the first emitted value from the flow
        val result = permissionImplementation.locationPermissionFlow.first()

        // Assert: The permission state should be true
        assertTrue(result)
    }

    @Test
    fun `locationPermissionFlow should emit false when permission is denied`() = runTest {
        // Arrange: Simulate permission is denied
        mockPermissionStatus(PackageManager.PERMISSION_DENIED)

        // Reinitialize the class to apply new permission state
        permissionImplementation = PermissionImplementation(mockContext)

        // Act: Collect the first emitted value from the flow
        val result = permissionImplementation.locationPermissionFlow.first()

        // Assert: The permission state should be false
        assertFalse(result)
    }

    @Test
    fun `updateLocationPermissionState should reflect permission change`() = runTest {
        // Arrange: Simulate permission is denied initially
        mockPermissionStatus(PackageManager.PERMISSION_DENIED)
        permissionImplementation = PermissionImplementation(mockContext)

        // Ensure initial state is false
        assertFalse(permissionImplementation.locationPermissionFlow.first())

        // Act: Simulate granting permission and update state
        mockPermissionStatus(PackageManager.PERMISSION_GRANTED)
        permissionImplementation.updateLocationPermissionState()

        // Assert: The state should now be true
        assertTrue(permissionImplementation.locationPermissionFlow.first())
    }
}
