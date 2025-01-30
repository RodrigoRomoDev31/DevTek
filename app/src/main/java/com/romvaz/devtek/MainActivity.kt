package com.romvaz.devtek

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.romvaz.core.data.location.LocationService
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.core.ui.navigation.NavigatorHandler
import com.romvaz.core.ui.theme.DevTekTheme
import com.romvaz.feature.home.homeGraph
import com.romvaz.feature.login.loginGraph
import com.romvaz.feature.user.userGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint // Hilt entry point for dependency injection
class MainActivity : ComponentActivity() {

    // Injected Navigator instance for navigation purposes
    @Inject
    lateinit var navigator: Navigator

    @OptIn(ExperimentalPermissionsApi::class) // Opt-in for experimental permissions API
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display (full-screen experience)
        setContent {
            // Set content to use window insets for better layout
            WindowCompat.setDecorFitsSystemWindows(window, false)

            // Remember navController for navigation
            val navController = rememberNavController()

            // Permission state for location permission
            val locationPermissionState = rememberPermissionState(
                permission = android.Manifest.permission.ACCESS_FINE_LOCATION
            )

            // Launcher for handling multiple permissions requests
            val requestPermissionLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

            var permissionsRequested by remember { mutableStateOf(false) }

            // Handle permission request logic on state change
            LaunchedEffect(key1 = locationPermissionState.status) {
                val permissionsToRequest = mutableListOf<String>()

                // If location permission is denied, add it to the list of permissions to request
                if (locationPermissionState.status is PermissionStatus.Denied) {
                    permissionsToRequest.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
                } else {
                    // If permission is granted, manage the location service
                    manageLocationService()
                }

                // Check for notification permission for devices running Android 13 (API 33) or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionsToRequest.add(android.Manifest.permission.POST_NOTIFICATIONS)
                }

                // If there are any permissions to request, launch the permission request
                if (permissionsToRequest.isNotEmpty() && !permissionsRequested) {
                    permissionsRequested = true
                    requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
                }
            }

            // Set the theme and build the UI
            DevTekTheme {
                Column {
                    // Setup the navigation host with multiple graphs
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute.RootRoute.route,
                        modifier = Modifier.weight(1.0f)
                    ) {
                        // Define the navigation graphs
                        loginGraph()
                        homeGraph()
                        userGraph()
                    }
                }
                // Handle navigation actions with the injected navigator
                NavigatorHandler(navigator = navigator, navController = navController)
            }
        }
    }

    // Starts the location service when location permission is granted
    private fun manageLocationService() {
        // Start the LocationService with an action to begin the service
        Intent(this@MainActivity, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }
}

