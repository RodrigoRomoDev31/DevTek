package com.romvaz.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.romvaz.core.ui.R
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.TypographyExtensions.captions
import com.romvaz.core.ui.theme.TypographyExtensions.h2
import com.romvaz.core.ui.theme.isDarkTheme
import com.romvaz.core.ui.utils.GlobalUtils

@Composable
fun NoInternetComponent(
    screenInStack: Boolean = true,
    goBackCallback: () -> Unit = {}
) {
    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_no_internet))

    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(Spacings.six),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        VerticalSpacer(height = 132.dp)

        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(300.dp)
                .scale(1f),
            isPlaying = true,
            iterations = LottieConstants.IterateForever,
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = stringResource(id = R.string.no_internet_connection),
            style = MaterialTheme.typography.h2.copy(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.padding(bottom = Spacings.three),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.no_internet_connection_disclaimer),
            style = MaterialTheme.typography.captions.copy(MaterialTheme.colorScheme.onSurface),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = Spacings.six)
        ) {
            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    GlobalUtils.goToSettings(context)
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.btn_open_settings)
                    )
                },
                style = ButtonStyle.Primary
            )

            VerticalSpacer(height = Spacings.four)

            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    goBackCallback()
                },
                text = {
                    Text(
                        text =
                        if (screenInStack) stringResource(id = R.string.btn_go_back)
                        else stringResource(id = R.string.btn_close_app)
                    )
                },
                style = ButtonStyle.Secondary
            )
        }
    }

    rememberSystemUiController().setStatusBarColor(
        color = Color.Transparent,
        darkIcons = !isDarkTheme()
    )
}
