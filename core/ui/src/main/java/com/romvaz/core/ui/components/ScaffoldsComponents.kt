package com.romvaz.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun DevTekScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    header: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = header,
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        bottomBar = bottomBar,
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevTekHeader(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    primaryAction: @Composable () -> Unit = {},
    secondaryActions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            title()
        },
        navigationIcon = { primaryAction() },
        actions = {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                secondaryActions()
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevTekTransparentHeader(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconDescription: String? = null,
    primaryAction: @Composable () -> Unit = {},
    secondaryActions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    iconTint: Color = Color.Unspecified
) {
    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
        )

        CenterAlignedTopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    icon?.let {
                        Icon(
                            painter = icon,
                            contentDescription = iconDescription,
                            tint = iconTint
                        )
                    }
                }
            },
            navigationIcon = { primaryAction() },
            actions = {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    secondaryActions()
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            scrollBehavior = scrollBehavior,
            modifier = modifier
        )
    }
}
