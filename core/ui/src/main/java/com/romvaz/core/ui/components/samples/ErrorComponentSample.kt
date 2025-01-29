package com.romvaz.core.ui.components.samples

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.romvaz.core.ui.R
import com.romvaz.core.ui.components.ErrorComponent

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorComponentSample() {
    ErrorComponent(
        title = { Text(stringResource(R.string.error_service_tittle)) },
        message = { Text(stringResource(R.string.error_service_messsage)) }
    )
}
