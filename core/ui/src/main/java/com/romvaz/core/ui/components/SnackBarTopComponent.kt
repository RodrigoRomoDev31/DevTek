package com.romvaz.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.romvaz.core.ui.R
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.TypographyExtensions.captionsBold
import com.romvaz.core.ui.theme.devTekColors

/**
 * @param modifier Modifier to be applied to the Snackbar's layout. Default is `Modifier`.
 * @param hostState The state of the Snackbar, used to show and manage Snackbar messages.
 * Default is a new instance of `SnackbarHostState()`.
 * @param snackBarTopStatus The status of the Snackbar, defining its visual appearance (e.g., SUCCESS, ERROR, WARNING).
 * @param onClickAction A lambda function that executes when the primary action button of the
 * Snackbar is clicked. Default is an empty lambda `{}`.
 * @param infoClickAction A lambda function that executes when the informational action.
 */
@Preview
@Composable
fun SnackBarTopComponent(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState = SnackbarHostState(),
    snackBarTopStatus: SnackBarTopStatus = SnackBarTopStatus.SUCCESS,
    onClickAction: () -> Unit = {},
    infoClickAction: () -> Unit = {}
) {
    val modifierSnackbar = Modifier
        .height(75.dp)
        .padding(horizontal = Spacings.two)
        .border(
            2.dp,
            snackBarTopStatus.getBorderColor(),
            RoundedCornerShape(Spacings.two)
        )
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                with(LocalDensity.current) {
                    PaddingValues(
                        top = WindowInsets.statusBars
                            .getTop(this)
                            .toDp(),
                    )
                }
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        SnackbarHost(
            modifier = modifier.padding(Spacings.one),
            hostState = hostState,
            snackbar = { data ->
                Snackbar(
                    modifier = modifierSnackbar.clickable { onClickAction() },
                    shape = RoundedCornerShape(Spacings.two),
                    contentColor = snackBarTopStatus.getContentColor(),
                    containerColor = snackBarTopStatus.getBackgroundColor(),
                    action = {
                        if (hostState.currentSnackbarData?.visuals?.withDismissAction == true)
                            Box(
                                modifier = Modifier.fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton(
                                    onClick = { onClickAction() }) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null,
                                        tint = MaterialTheme.devTekColors.Primary100
                                    )
                                }
                            }
                    },
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(Spacings.six),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = Spacings.one),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = modifier.size(24.dp),
                                imageVector = snackBarTopStatus.getIcon(),
                                contentDescription = null,
                                tint = if (snackBarTopStatus == SnackBarTopStatus.ERROR)
                                    Color.Red else MaterialTheme.colorScheme.onSurface
                            )

                            Column(
                                Modifier
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                ProvideTextStyle(
                                    value = MaterialTheme.typography.captionsBold
                                        .copy(
                                            fontWeight = if (snackBarTopStatus == SnackBarTopStatus.ERROR)
                                                FontWeight.Bold else FontWeight.Medium
                                        )
                                ) {
                                    data.visuals.message.let {
                                        Text(
                                            text = it,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.wrapContentWidth()

                                        )
                                    }
                                }

                                //Only show this text when Status == Internet
                                // IN needed, make it dynamic and pass the resource
                                if (snackBarTopStatus == SnackBarTopStatus.INTERNET)
                                    ProvideTextStyle(
                                        value = MaterialTheme.typography.captions
                                            .copy(
                                                fontWeight = FontWeight.Medium
                                            )
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.btn_open_settings),
                                            maxLines = 1,
                                            modifier = Modifier
                                                .wrapContentWidth()
                                                .padding(top = Spacings.two)
                                                .clickable { infoClickAction() },
                                            textDecoration = TextDecoration.Underline

                                        )
                                    }
                            }
                        }
                    }
                )
            }
        )
    }
}

// Get Content color by snack status
@Composable
fun SnackBarTopStatus.getContentColor(): Color = when (this) {
    SnackBarTopStatus.INTERNET,
    SnackBarTopStatus.SUCCESS,
    SnackBarTopStatus.ERROR -> MaterialTheme.colorScheme.onSurface

}

// Get background color by snack status
@Composable
fun SnackBarTopStatus.getBackgroundColor(): Color = when (this) {
    SnackBarTopStatus.INTERNET,
    SnackBarTopStatus.SUCCESS,
    SnackBarTopStatus.ERROR -> MaterialTheme.colorScheme.surface
}

// Get Border Color by snack status
@Composable
fun SnackBarTopStatus.getBorderColor(): Color = when (this) {
    SnackBarTopStatus.INTERNET,
    SnackBarTopStatus.SUCCESS,
    SnackBarTopStatus.ERROR -> MaterialTheme.devTekColors.Primary100
}

// Get Border Color by snack status
@Composable
fun SnackBarTopStatus.getIcon(): ImageVector = when (this) {
    SnackBarTopStatus.SUCCESS -> Icons.Filled.CheckCircle
    SnackBarTopStatus.ERROR -> Icons.Filled.Warning
    SnackBarTopStatus.INTERNET -> Icons.Default.Refresh
}

// Enum for snack bar type
//Add types if needed
enum class SnackBarTopStatus {
    SUCCESS,
    ERROR,
    INTERNET
}

// Custom Class if more fields are needed
data class SnackBarVisuals(
    override val message: String,
    override val withDismissAction: Boolean = false,
    override val actionLabel: String = "",
    override val duration: SnackbarDuration = SnackbarDuration.Short,
) : SnackbarVisuals
