package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.ui.screens.MainScreen
import com.example.notesapp.ui.screens.OnBoardingScreen
import com.example.notesapp.ui.screens.SplashScreen

@Composable
fun Navigation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name) {
        composable(route = Screens.SplashScreen.name) {
            SplashScreen(navController = navController, isFirstTime = true)
        }
        composable(route = Screens.OnBoardingScreen.name) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screens.HomeScreen.name) {
            MainScreen(navController = navController)
        }
    }
}