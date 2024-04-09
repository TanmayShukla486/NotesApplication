package com.example.notesapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    textColor: Color,
    fontFamily: FontFamily,
    fontSize: Int,
    align: TextAlign = TextAlign.Unspecified,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = fontFamily,
            fontSize = (fontSize).sp,
            color = textColor,
            textAlign = align
        )
    )
}