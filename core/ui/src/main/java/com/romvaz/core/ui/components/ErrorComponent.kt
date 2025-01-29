package com.romvaz.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.romvaz.core.ui.R
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.TypographyExtensions.h5
import com.romvaz.core.ui.theme.devTekColors

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    errorIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
    messageColor: Color = MaterialTheme.devTekColors.Gray30,
    buttonMessage: String? = null,
    onClick: () -> Unit = {},
    message: @Composable () -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = messageColor,
        modifier = modifier
            .padding(Spacings.six)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Spacings.eight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = errorIcon,
                contentDescription = null,
                tint = MaterialTheme.devTekColors.White
            )
            VerticalSpacer(height = Spacings.four)
            ProvideTextStyle(
                value = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.devTekColors.White,
                    textAlign = TextAlign.Center
                )
            ) {
                title()
            }
            VerticalSpacer(height = Spacings.four)
            ProvideTextStyle(
                value = MaterialTheme.typography.captions.copy(
                    color = MaterialTheme.devTekColors.White,
                    textAlign = TextAlign.Center
                )
            ) {
                message()
            }
            if (buttonMessage != null) {
                ButtonComponent(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClick,
                    text = { Text(text = buttonMessage) },
                    style = ButtonStyle.Secondary,
                    size = ButtonSize.Small
                )
            }

        }
    }
}
