package com.romvaz.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.romvaz.core.ui.R

private val roboto = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_light, FontWeight.Light),
)

val Typography = Typography()

object TypographyExtensions {

    val Typography.h1: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 44.sp,
            lineHeight = 44.sp,
        )

    val Typography.h2: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            lineHeight = 32.sp,
        )

    val Typography.h3: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 28.sp,
            lineHeight = 28.sp,
        )

    val Typography.h4: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            lineHeight = 20.sp,
        )

    val Typography.h5: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 19.sp,
        )

    val Typography.paragraph: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 36.sp,
        )

    val Typography.body: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 24.sp,
        )

    val Typography.subtitles: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 20.sp,
        )

    val Typography.labels: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 21.sp,
        )

    val Typography.captions: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 17.sp,
        )

    val Typography.captionsBold: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 21.sp,
        )

    val Typography.utility: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 14.sp,
        )

    val Typography.utilityUppercase: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 12.sp,
        )

    val Typography.utilityUppercaseBold: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 12.sp
        )

    val Typography.extraSmall: TextStyle
        get() = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 10.sp,
        )

}
