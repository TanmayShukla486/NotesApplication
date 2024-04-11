package com.example.notesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.navigation.Screens
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.secondaryColor

@Composable
fun TopNavBar(
    title: String,
    screen: Screens
) {
    val icon = when(screen) {
        Screens.SplashScreen -> null
        Screens.HomeScreen -> Icons.Outlined.Delete
        Screens.OnBoardingScreen -> null
    }
    val rowModifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .padding(horizontal = 8.dp)
    Box(modifier = Modifier.background(secondaryColor)) {
        Row (
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = title, textColor = Color.Black, fontFamily = modernFamily, fontSize = 50)
            if (icon != null)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewTopNavBar() {
    TopNavBar(
        title = "Notes",
        screen = Screens.HomeScreen
    )
}