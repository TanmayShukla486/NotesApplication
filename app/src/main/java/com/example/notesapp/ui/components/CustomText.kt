package com.example.notesapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
    text: String,
    textColor: Color,
    fontFamily: FontFamily,
    fontSize: Int,
    align: TextAlign = TextAlign.Unspecified,
    onClick: () -> Unit = {}
) {
    val textModifier = if (clickable)
        modifier.clickable { onClick.invoke() } else modifier
    Text(
        modifier = textModifier,
        text = text,
        style = TextStyle(
            fontFamily = fontFamily,
            fontSize = (fontSize).sp,
            color = textColor,
            textAlign = align,
            shadow = Shadow(
                color = Color.DarkGray,
                offset = Offset(1f,1.5f)
            )
        ),
    )
}