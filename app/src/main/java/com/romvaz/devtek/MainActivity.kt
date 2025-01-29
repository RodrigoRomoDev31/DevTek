package com.romvaz.devtek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.romvaz.core.domain.routes.HomeRoute
import com.romvaz.core.ui.navigation.Navigator
import com.romvaz.core.ui.navigation.NavigatorHandler
import com.romvaz.core.ui.theme.DevTekTheme
import com.romvaz.feature.home.homeGraph
import com.romvaz.feature.login.loginGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val navController = rememberNavController()
            DevTekTheme {
                Column {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute.RootRoute.route,
                        modifier = Modifier.weight(1.0f)
                    ) {
                        loginGraph()
                        homeGraph()
                    }
                }
                NavigatorHandler(navigator = navigator, navController = navController)
            }
        }
    }
}
