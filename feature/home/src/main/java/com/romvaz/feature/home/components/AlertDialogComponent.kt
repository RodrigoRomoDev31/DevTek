package com.romvaz.feature.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.InputComponent
import com.romvaz.core.ui.components.VerticalSpacer
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
    onClickConfirm: (String) -> Unit
) {
    var problem by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = {
            if (title.isNotEmpty())
                Text(text = title, style = MaterialTheme.typography.h4)
        },
        text = {
            Column(modifier.wrapContentHeight()) {
                Text(text = message, style = MaterialTheme.typography.captions)
                VerticalSpacer(Spacings.two)
                InputComponent(
                    value = problem,
                    onValueChange = { problem = it },
                    placeholder = stringResource(R.string.describe_problem),
                    singleLine = false,
                    lastField = true,
                    isPassword = false
                )
            }
        },
        confirmButton = {
            Text(
                text = textConfirmButton,
                style = MaterialTheme.typography.utilityUppercaseBold,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = Spacings.three)
                    .clickable {
                        onClickConfirm(problem)
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
