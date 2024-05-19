package com.example.notesapp.navigation

import android.content.ContentResolver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.ui.components.VideoNote
import com.example.notesapp.ui.screens.AddNoteScreen
import com.example.notesapp.ui.screens.BasicNoteScreen
import com.example.notesapp.ui.screens.DisplayVideoScreen
import com.example.notesapp.ui.screens.MainScreen
import com.example.notesapp.ui.screens.NoteDetailsScreen
import com.example.notesapp.ui.screens.OnBoardingScreen
import com.example.notesapp.ui.screens.SplashScreen

@Composable
fun Navigation (
    notesViewModel: NoteViewModel,
    context: ContentResolver,
) {
    val navController = rememberNavController()
    val onCreateNote = {
        navController.navigate(Screens.AddNoteScreen.name)
    }
    val onGoBack = {
        navController.navigate(Screens.HomeScreen.name)
    }

    NavHost(navController = navController, startDestination = Screens.SplashScreen.name) {

        composable(route = Screens.SplashScreen.name) {
            SplashScreen(navController = navController, isFirstTime = true)
        }

        composable(route = Screens.OnBoardingScreen.name) {
            OnBoardingScreen(navController = navController)
        }

        composable(route = Screens.HomeScreen.name) {
            MainScreen(
                onClickNewNote = onCreateNote,
                notesViewModel = notesViewModel,
                navController = navController
            )
        }

        composable(route = Screens.AddNoteScreen.name) {
            AddNoteScreen(
                navController = navController,
                notesViewModel = notesViewModel
            )
        }

        composable(route = Screens.NoteDisplayScreen.name+"/{noteId}/{isEditable}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                },
                navArgument("isEditable") {
                    type = NavType.BoolType
                }
            )) {
            arguments ->
            val boolParameter = arguments.arguments?.getBoolean("isEditable")
            val isEditable: MutableState<Boolean> = remember {
                mutableStateOf(
                    boolParameter ?: false
                )
            }
            val idParameter = arguments.arguments?.getString("noteId", "")
            NoteDetailsScreen(
                isEditable = isEditable,
                noteViewModel = notesViewModel,
                onGoBack = onGoBack,
                id = idParameter!!
            )
        }

        composable(route = Screens.DisplayVideo.name + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                }
            )) {
            arguments ->
            val idParameter = arguments.arguments?.getString("noteId")
            DisplayVideoScreen(
                noteViewModel = notesViewModel,
                onBackClick = onGoBack,
                id = idParameter ?: ""
            )
        }

        composable(route = Screens.VideoNoteScreen.name) {
            VideoNote(onExitClick = onGoBack, notesViewModel = notesViewModel)
        }
        composable(route = Screens.ImageNoteScreen.name) {
            BasicNoteScreen(
                notesViewModel = notesViewModel,
                onExitClick = onGoBack
            )
        }

    }
}