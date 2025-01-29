package com.romvaz.core.ui.components.samples

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.romvaz.core.ui.components.ButtonComponent
import com.romvaz.core.ui.components.ButtonSize
import com.romvaz.core.ui.components.ButtonStyle
import com.romvaz.core.ui.theme.DevTekTheme
import com.romvaz.core.ui.theme.Spacings

@Suppress("LongMethod")
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonsEnablePreview() {
    DevTekTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(Spacings.six),
            verticalArrangement = Arrangement.spacedBy(Spacings.five)
        ) {
            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Primary,
                size = ButtonSize.Medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Primary Medium")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Primary,
                size = ButtonSize.Small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Primary Small")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Secondary,
                size = ButtonSize.Medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Secondary Medium")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Secondary,
                size = ButtonSize.Small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Secondary Small")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Tertiary,
                size = ButtonSize.Medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Tertiary Medium")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Tertiary,
                size = ButtonSize.Small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Tertiary Small")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Alternative,
                size = ButtonSize.Medium, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Alternative Medium")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Alternative,
                size = ButtonSize.Small, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Alternative Small")
            }
        }
    }
}

@Suppress("LongMethod")
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonsDisablePreview() {
    DevTekTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(Spacings.six),
            verticalArrangement = Arrangement.spacedBy(Spacings.five)
        ) {
            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Primary,
                size = ButtonSize.Medium,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Primary Medium Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Primary,
                size = ButtonSize.Small,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Primary Small Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Secondary,
                size = ButtonSize.Medium,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Secondary Medium Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Secondary,
                size = ButtonSize.Small,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Secondary Small Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Tertiary,
                size = ButtonSize.Medium,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Tertiary Medium Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Tertiary,
                size = ButtonSize.Small,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Tertiary Small Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Alternative,
                size = ButtonSize.Medium, modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Alternative Medium Disable")
            }

            ButtonComponent(
                onClick = {},
                style = ButtonStyle.Alternative,
                size = ButtonSize.Small, modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "Alternative Small Disable")
            }
        }
    }
}
