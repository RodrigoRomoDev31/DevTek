package com.romvaz.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Suppress("ConstructorParameterNaming")
@Immutable
data class DevTekColors(
    val Black: Color = Colors.Black,
    val White: Color = Colors.White,
    val Transparent: Color = Colors.Transparent,

    // Gray scale
    val Gray60: Color = Colors.Gray60,
    val Gray50: Color = Colors.Gray50,
    val Gray40: Color = Colors.Gray40,
    val Gray30: Color = Colors.Gray30,
    val Gray20: Color = Colors.Gray20,
    val Gray10: Color = Colors.Gray10,

    // Primary
    val Primary100: Color = Colors.Primary100,
    val Primary90: Color = Colors.Primary90,
    val Primary80: Color = Colors.Primary80,
    val Primary70: Color = Colors.Primary70,
    val Primary60: Color = Colors.Primary60,
    val Primary50: Color = Colors.Primary50,
    val Primary40: Color = Colors.Primary40,
    val Primary30: Color = Colors.Primary30,
    val Primary20: Color = Colors.Primary20,
    val Primary10: Color = Colors.Primary10,

    //Secondary
    val Secondary60: Color = Colors.Secondary60,
    val Secondary50: Color = Colors.Secondary50,
    val Secondary40: Color = Colors.Secondary40,
    val Secondary30: Color = Colors.Secondary30,
    val Secondary20: Color = Colors.Secondary20,

    // Opacity
    val OpacityLight: Color = Colors.OpacityLight,
    val OpacityDark: Color = Colors.OpacityDark,

    val messageBackground: Color = Colors.Gray20,
    val dividerColor: Color = Colors.Gray10,

    //Rating Notification Colors
    val notificationWarning: Color = Colors.Yellow,
    val notificationSucces: Color = Colors.Green,
    val notificationError: Color = Colors.Red,
)
