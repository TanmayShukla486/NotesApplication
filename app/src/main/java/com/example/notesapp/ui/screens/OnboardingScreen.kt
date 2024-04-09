package com.example.notesapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column (
        modifier = modifier
            .background(color = Color(0xffF3725E))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Preview (showBackground = true)
@Composable
fun PreviewOnBoardingScreen() {
    OnBoardingScreen(navController = rememberNavController())
}