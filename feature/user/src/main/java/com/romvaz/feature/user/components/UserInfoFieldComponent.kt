package com.romvaz.feature.user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.romvaz.core.ui.components.VerticalSpacer
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.TypographyExtensions.captionsBold
import com.romvaz.core.ui.theme.devTekColors

@Composable
fun UserInfoFieldComponent(
    fieldType: String,
    filedContent: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = Spacings.horizontalMargin, vertical = Spacings.four),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = fieldType,
            style = MaterialTheme.typography.captionsBold.copy(MaterialTheme.devTekColors.Primary100)
        )
        VerticalSpacer(Spacings.two)
        Text(
            text = filedContent,
            style = MaterialTheme.typography.captions.copy(MaterialTheme.colorScheme.onSurface)
        )
    }
}
