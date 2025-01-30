package com.romvaz.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.romvaz.core.ui.R
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.theme.isDarkTheme

/**
 * @param value The current text value displayed in the field.
 * @param onValueChange A lambda function that is triggered when the text value changes.
 * It receives the new text value as a parameter.
 * @param placeholder The placeholder text to be displayed when the field is empty.
 * @param singleLine A flag indicating whether the text field should be limited to a single line. Default is `true`.
 * @param lastField A flag indicating whether this is the last field in a form, affecting layout behavior.
 * @param isPassword A flag indicating whether the field is for password input, hiding the text with asterisks.
 * Default is `false`.
 */
@Composable
fun InputComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    lastField: Boolean,
    isPassword: Boolean = false
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(Spacings.two)
            )
            .focusRequester(focusRequester)
            .focusable(false),
        visualTransformation =
        // Transform input if its password and visibility is true or false
        if (passwordVisible && isPassword) VisualTransformation.None
        else if (!isPassword) VisualTransformation.None
        else PasswordVisualTransformation(),
        textStyle = TextStyle(fontStyle = FontStyle.Normal),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.captions
                    .copy(
                        if (isDarkTheme()) MaterialTheme.devTekColors.Gray20
                        else MaterialTheme.devTekColors.Gray40
                    )
            )
        },
        trailingIcon = {
            // SHOW ICON TO INMASCade content in input
            if (isPassword) {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.ic_invisible)
                else painterResource(id = R.drawable.ic_visible)

                val description = if (passwordVisible) "Hide password" else "Show password"

                // Update Password visibility
                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    modifier = Modifier.padding(end = Spacings.two)
                ) {
                    Icon(
                        painter = image,
                        contentDescription = description,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        singleLine = singleLine,
        shape = RoundedCornerShape(8.dp),
        colors = getColors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email,
            imeAction = if (lastField) ImeAction.Done else ImeAction.Next
        )
    )
}

// CONFIGURE COLORS DEPENDING IF IS DARK THEME
@Composable
private fun getColors(): TextFieldColors =
    TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        cursorColor = MaterialTheme.devTekColors.Primary80,
        unfocusedLeadingIconColor = MaterialTheme.devTekColors.Gray20,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        focusedLabelColor = if (isDarkTheme()) {
            MaterialTheme.devTekColors.Gray20
        } else {
            MaterialTheme.devTekColors.Gray40
        },
        unfocusedLabelColor = if (isDarkTheme()) {
            MaterialTheme.devTekColors.Gray20
        } else {
            MaterialTheme.devTekColors.Gray60
        }
    )
