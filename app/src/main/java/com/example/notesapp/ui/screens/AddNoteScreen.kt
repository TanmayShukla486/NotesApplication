package com.example.notesapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.navigation.Screens
import com.example.notesapp.ui.components.SelectNoteScreenBody
import com.example.notesapp.ui.theme.primaryColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    notesViewModel: NoteViewModel
) {

    val scope = rememberCoroutineScope()
    val onNextClick: (String) -> Unit = {
        when (it) {
            "Select" -> navController.navigate(Screens.AddNoteScreen.name)
            "Normal" -> navController.navigate(Screens.ImageNoteScreen.name)
            "Video" -> navController.navigate(Screens.VideoNoteScreen.name)
        }
    }

    val onExitClick: () -> Unit = {
        navController.navigate(Screens.HomeScreen.name)
    }
    val outerContainerModifier = modifier
        .fillMaxSize()
        .background(color = primaryColor)
    Box(
        modifier = outerContainerModifier
    ) {
        SelectNoteScreenBody(
            onNextClick = onNextClick,
            onExitClick =  onExitClick
        )
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewAddNoteScreen() {
    AddNoteScreen(
        navController = rememberNavController(),
        notesViewModel = viewModel()
    )
}