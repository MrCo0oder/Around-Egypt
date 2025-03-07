package com.example.aroundegypt.presentaion.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R


val GothamRounded = FontFamily(Font(R.font.gotham_rnd_bold, FontWeight.Bold))
val Gotham = FontFamily(
    Font(R.font.gotham_bold, FontWeight.Bold),
    Font(R.font.gotham_medium, FontWeight.Medium),
    Font(R.font.gotham_regular, FontWeight.Normal)
)
val Inter = FontFamily(Font(R.font.inter))

val AppTypography = Typography(
    titleLarge = TextStyle( // Style 1
        fontFamily = GothamRounded,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle( // Style 2
        fontFamily = Gotham,
        fontWeight = FontWeight.Bold, // 700
        fontSize = 22.sp
    ),
    titleSmall = TextStyle( // Style 3
        fontFamily = Gotham,
        fontWeight = FontWeight.Bold, // 700
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle( // Style 4
        fontFamily = Gotham,
        fontWeight = FontWeight.Bold, // 700
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle( // Style 5
        fontFamily = Gotham,
        fontWeight = FontWeight.Medium, // 500
        fontSize = 14.sp
    ),
    bodySmall = TextStyle( // Style 6
        fontFamily = Gotham,
        fontWeight = FontWeight.Normal, // 400
        fontSize = 17.sp
    ),
    labelLarge = TextStyle( // Style 7
        fontFamily = Inter,
        fontWeight = FontWeight.Normal, // 400
        fontSize = 17.sp
    )
)
