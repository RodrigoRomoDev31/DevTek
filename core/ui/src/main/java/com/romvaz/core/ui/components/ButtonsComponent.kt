package com.romvaz.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.h5
import com.romvaz.core.ui.theme.TypographyExtensions.utility
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.theme.isDarkTheme
import kotlinx.coroutines.delay

@Suppress("LongParameterList")
@Composable
fun ButtonComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    style: ButtonStyle = ButtonStyle.Primary,
    size: ButtonSize = ButtonSize.Medium,
    enabled: Boolean = true,
    debounceLength: Long = 1000,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    text: @Composable () -> Unit,
) {

    var allowClicks by remember {
        mutableStateOf(true)
    }

    Button(
        onClick = {
            if (allowClicks) {
                allowClicks = false
                onClick()
            }
        },
        shape = size.getShape(),
        colors = style.getColors(),
        border = style.getBorder(),
        modifier = modifier.heightIn(size.getHeight()),
        enabled = enabled && allowClicks,
        contentPadding = contentPadding,
        elevation = elevation
    ) {
        ProvideTextStyle(value = size.getTextStyle()) {
            text()
        }
        icon?.let {
            Spacer(modifier.padding(horizontal = Spacings.two))
            icon()
        }
    }

    LaunchedEffect(allowClicks) {
        if (!allowClicks) {
            delay(debounceLength)
            allowClicks = true
        }
    }

}

enum class ButtonSize {
    Small, Medium
}

enum class ButtonStyle {
    Primary, Secondary, Alternative, Tertiary
}

@Composable
private fun ButtonSize.getShape(): Shape = when (this) {
    ButtonSize.Small -> MaterialTheme.shapes.small
    ButtonSize.Medium -> MaterialTheme.shapes.large
}

@Composable
private fun ButtonSize.getHeight(): Dp = when (this) {
    ButtonSize.Small -> 30.dp
    ButtonSize.Medium -> 56.dp
}

@Composable
private fun ButtonStyle.getColors(): ButtonColors = when (this) {
    ButtonStyle.Primary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.devTekColors.White,
        disabledContainerColor = MaterialTheme.devTekColors.Gray20,
        disabledContentColor = MaterialTheme.devTekColors.Gray50
    )

    ButtonStyle.Secondary -> ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = if (isDarkTheme()) {
            MaterialTheme.colorScheme.inverseOnSurface
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        disabledContainerColor = Color.Transparent,
        disabledContentColor = if (isDarkTheme()) {
            MaterialTheme.devTekColors.Gray30
        } else {
            MaterialTheme.devTekColors.Gray40
        }
    )

    ButtonStyle.Alternative -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.devTekColors.Gray10,
        contentColor = MaterialTheme.colorScheme.primary
    )

    ButtonStyle.Tertiary -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.devTekColors.Gray10,
        contentColor = MaterialTheme.devTekColors.Secondary50,
        disabledContainerColor = MaterialTheme.devTekColors.Secondary20,
        disabledContentColor = MaterialTheme.devTekColors.Secondary30
    )
}

@Composable
private fun ButtonStyle.getBorder(): BorderStroke? = when (this) {
    ButtonStyle.Primary -> null
    ButtonStyle.Secondary -> BorderStroke(1.dp, MaterialTheme.devTekColors.Primary100)

    ButtonStyle.Alternative -> null
    ButtonStyle.Tertiary -> null
}

@Composable
private fun ButtonSize.getTextStyle(): TextStyle = when (this) {
    ButtonSize.Small -> MaterialTheme.typography.utility
    ButtonSize.Medium -> MaterialTheme.typography.h5
}
