package com.romvaz.core.ui.components.samples

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.romvaz.core.ui.components.DevTekHeader
import com.romvaz.core.ui.components.DevTekScaffold
import com.romvaz.core.ui.theme.DevTekTheme
import com.romvaz.core.ui.theme.TypographyExtensions.h2
import com.romvaz.core.ui.theme.devTekColors

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScaffoldPreview() {
    DevTekTheme {
        DevTekScaffold(
            header = {
                DevTekHeader(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Header",
                                style = MaterialTheme.typography.h2.copy(MaterialTheme.devTekColors.White)
                            )
                        }
                    },
                    primaryAction = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = null,
                            tint = MaterialTheme.devTekColors.White
                        )
                    },
                    secondaryActions = {
                        Icon(
                            imageVector = Icons.Filled.Settings, contentDescription = null,
                            tint = MaterialTheme.devTekColors.White
                        )
                    }
                )
            }
        ) { paddings ->
            Box(
                modifier = Modifier
                    .padding(paddings)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Scaffold Preview", color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
