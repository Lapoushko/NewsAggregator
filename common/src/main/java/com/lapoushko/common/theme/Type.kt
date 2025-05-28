package com.lapoushko.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lapoushko.common.R

val fontFamily = FontFamily(
    Font(
        R.font.roboto, FontWeight.Black
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        color = White
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        color = White
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        color = White
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        color = White
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontSize = 28.sp,
        color = White
    )
)