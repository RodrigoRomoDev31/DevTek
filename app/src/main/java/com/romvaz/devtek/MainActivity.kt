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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator


    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val navController = rememberNavController()
            val locationPermissionState = rememberPermissionState(
                permission = android.Manifest.permission.ACCESS_FINE_LOCATION
            )

            val requestPermissionLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

            LaunchedEffect(key1 = locationPermissionState.status) {
                val permissionsToRequest = mutableListOf<String>()

                if (locationPermissionState.status is PermissionStatus.Denied) {
                    permissionsToRequest.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
                } else
                    manageLocationService()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionsToRequest.add(android.Manifest.permission.POST_NOTIFICATIONS)
                }

                if (permissionsToRequest.isNotEmpty()) {
                    requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
                }
            }

            DevTekTheme {
                Column {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute.RootRoute.route,
                        modifier = Modifier.weight(1.0f)
                    ) {
                        loginGraph()
                        homeGraph()
                        userGraph()
                    }
                }
                NavigatorHandler(navigator = navigator, navController = navController)
            }
        }
    }

    private fun manageLocationService() {
        Intent(this@MainActivity, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }
}
