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
            if (isPassword) {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.ic_invisible)
                else painterResource(id = R.drawable.ic_visible)

                val description = if (passwordVisible) "Hide password" else "Show password"

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


const val REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$"
const val REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@#\$%^&+=!]).{8,}\$"
fun String.validateMail() = Regex(REGEX_EMAIL).matches(this)
fun String.validatePassword() = Regex(REGEX_PASSWORD).matches(this)
