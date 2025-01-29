package com.romvaz.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.romvaz.core.ui.R

@Composable
fun LoadingComponent(
    scale: Float = 1f
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ic_loading))

    Dialog(onDismissRequest = { }) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.scale(scale),
            isPlaying = true,
            iterations = LottieConstants.IterateForever
        )
    }
}
