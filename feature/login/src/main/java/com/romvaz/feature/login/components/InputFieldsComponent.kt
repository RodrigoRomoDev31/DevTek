package com.romvaz.feature.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.InputComponent
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.devTekColors

@Composable
fun InputFieldsComponent(
    value: String,
    passWord: Boolean,
    onValueChange: (String) -> Unit
) {
    val title =
        if (passWord) stringResource(id = R.string.password) else stringResource(id = R.string.email)

    val placeHolder =
        if (passWord) "" else stringResource(id = R.string.email_placeholder)

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacings.six), horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.captions.copy(MaterialTheme.devTekColors.Primary100),
            modifier = Modifier.padding(bottom = Spacings.two)
        )
        InputComponent(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = placeHolder,
            lastField = passWord,
            isPassword = passWord
        )
    }
}
