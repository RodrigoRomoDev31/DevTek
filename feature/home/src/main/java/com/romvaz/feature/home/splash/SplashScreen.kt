package com.romvaz.feature.home.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.romvaz.core.ui.R
import com.romvaz.core.ui.theme.Spacings
import com.romvaz.core.ui.theme.devTekColors
import com.romvaz.core.ui.utils.DELAY_TIME_100
import com.romvaz.core.ui.utils.DELAY_TIME_166
import com.romvaz.core.ui.utils.SLIDE_IN_OFFSET_END
import com.romvaz.core.ui.utils.SLIDE_IN_OFFSET_START
import com.romvaz.core.ui.utils.SPLASH_ANIMATION_SIZE
import kotlinx.coroutines.delay

// Splash Screen, contains different LaunchEffects to show animation
@Suppress("UnusedParameter")
@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    var showAnimation by remember { mutableStateOf(false) }
    var currentAnimationIndex by remember { mutableIntStateOf(0) }
    val numberOfAnimations = List(SPLASH_ANIMATION_SIZE) { it }
    var containerWidth by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(showAnimation) {
        if (showAnimation) {
            for (index in numberOfAnimations.indices) {
                delay(DELAY_TIME_100)
                currentAnimationIndex = index
            }
        }
    }

    LaunchedEffect(key1 = showAnimation) {
        if (!showAnimation) {
            delay(DELAY_TIME_166)
            showAnimation = true
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.devTekColors.Primary80)
            .fillMaxSize()
            .padding(top = Spacings.six, start = Spacings.six, end = Spacings.six)
            .onSizeChanged { size ->
                containerWidth = size.width.toFloat()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            numberOfAnimations.forEachIndexed { index, _ ->
                AnimatedVisibility(
                    visible = showAnimation && index <= currentAnimationIndex,
                    enter = slideIn(initialOffset = {
                        IntOffset(
                            SLIDE_IN_OFFSET_START,
                            SLIDE_IN_OFFSET_END
                        )
                    }),
                    exit = fadeOut(
                        targetAlpha = 0f,
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_devtek_logo),
                        contentDescription = "logo",
                        modifier = Modifier.padding(vertical = Spacings.six),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }

    rememberSystemUiController().setStatusBarColor(
        color = Color.Transparent,
        darkIcons = false
    )
}
