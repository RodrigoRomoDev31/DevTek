package com.romvaz.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.TypographyExtensions.h4
import com.romvaz.core.ui.theme.TypographyExtensions.utilityUppercaseBold
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.theme.isDarkTheme

@Composable
fun AlertDialogComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String,
    textConfirmButton: String,
    textCancelButton: String = "",
    onDismissRequest: () -> Unit = {},
    onClickDismiss: () -> Unit = {},
    onClickConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = {
            if (title.isNotEmpty())
                Text(text = title, style = MaterialTheme.typography.h4)
        },
        text = {
            Text(text = message, style = MaterialTheme.typography.captions)
        },
        confirmButton = {
            Text(
                text = textConfirmButton,
                style = MaterialTheme.typography.utilityUppercaseBold,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = Spacings.three)
                    .clickable {
                        onClickConfirm()
                    }
            )
        },
        dismissButton = {
            if (textCancelButton.isNotEmpty())
                Text(
                    text = textCancelButton,
                    style = MaterialTheme.typography.utilityUppercaseBold,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = Spacings.three)
                        .clickable {
                            onClickDismiss()
                        },
                    color = MaterialTheme.devTekColors.notificationError
                )
        },
        tonalElevation = 0.dp,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = if (isDarkTheme()) {
            MaterialTheme.devTekColors.Gray20
        } else {
            MaterialTheme.devTekColors.Gray50
        },
        containerColor = if (isDarkTheme()) {
            MaterialTheme.devTekColors.Black
        } else {
            MaterialTheme.devTekColors.White
        }
    )
}
