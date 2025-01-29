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
import com.romvaz.core.ui.theme.DevTekTheme
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.body
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.TypographyExtensions.captionsBold
import com.romvaz.core.ui.theme.TypographyExtensions.extraSmall
import com.romvaz.core.ui.theme.TypographyExtensions.h1
import com.romvaz.core.ui.theme.TypographyExtensions.h2
import com.romvaz.core.ui.theme.TypographyExtensions.h3
import com.romvaz.core.ui.theme.TypographyExtensions.h4
import com.romvaz.core.ui.theme.TypographyExtensions.h5
import com.romvaz.core.ui.theme.TypographyExtensions.labels
import com.romvaz.core.ui.theme.TypographyExtensions.paragraph
import com.romvaz.core.ui.theme.TypographyExtensions.subtitles
import com.romvaz.core.ui.theme.TypographyExtensions.utility
import com.romvaz.core.ui.theme.TypographyExtensions.utilityUppercase
import com.romvaz.core.ui.theme.TypographyExtensions.utilityUppercaseBold

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextStylesPreview() {
    DevTekTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(Spacings.six),
            verticalArrangement = Arrangement.spacedBy(Spacings.three)
        ) {
            Text(
                text = "H1",
                style = MaterialTheme.typography.h1.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "H2",
                style = MaterialTheme.typography.h2.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "H3",
                style = MaterialTheme.typography.h3.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "H4",
                style = MaterialTheme.typography.h4.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "H5",
                style = MaterialTheme.typography.h5.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Paragraph",
                style = MaterialTheme.typography.paragraph.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Body",
                style = MaterialTheme.typography.body.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Subtitles",
                style = MaterialTheme.typography.subtitles.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Labels",
                style = MaterialTheme.typography.labels.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Captions",
                style = MaterialTheme.typography.captions.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Captions Bold",
                style = MaterialTheme.typography.captionsBold.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Utility",
                style = MaterialTheme.typography.utility.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Utility Uppercase",
                style = MaterialTheme.typography.utilityUppercase.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Utility Uppercase Bold",
                style = MaterialTheme.typography.utilityUppercaseBold.copy(MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = "Extra Small",
                style = MaterialTheme.typography.extraSmall.copy(MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}
