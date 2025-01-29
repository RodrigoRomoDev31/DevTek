package com.romvaz.feature.home.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.DevTekScaffold
import com.romvaz.core.ui.components.DevTekTransparentHeader
import com.romvaz.core.ui.theme.devTekColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    DevTekScaffold(
        header = {
            DevTekTransparentHeader(
                icon = painterResource(R.drawable.ic_devtek_logo),
                iconTint = MaterialTheme.colorScheme.onSurface,
                primaryAction = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.devTekColors.notificationError
                        )
                    }
                },
                secondaryActions = {
                    IconButton(onClick = {
                        viewModel.navigateToUserInfo()
                    }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.devTekColors.Primary100
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            Text("DevTek")
        }
    }
}
