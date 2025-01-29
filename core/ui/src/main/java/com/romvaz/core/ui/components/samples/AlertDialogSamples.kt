package com.romvaz.core.ui.components.samples

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.romvaz.core.ui.components.AlertDialogComponent
import com.romvaz.core.ui.theme.DevTekTheme

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AlerDialogCancelPreview() {
    DevTekTheme {
        AlertDialogComponent(
            title = "Esto es un titulo",
            message = "Esto es un mensaje",
            textConfirmButton = "Confirmar",
            textCancelButton = "Cancelar",
            onClickConfirm = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AlerDialogPreview() {
    DevTekTheme {
        AlertDialogComponent(
            title = "",
            message = "Esto es un mensaje",
            textConfirmButton = "Confirmar",
            textCancelButton = "",
            onClickConfirm = {}
        )
    }
}
