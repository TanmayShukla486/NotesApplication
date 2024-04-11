package com.example.notesapp.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.navigation.Screens
import com.example.notesapp.ui.components.CreateNote
import com.example.notesapp.ui.components.SelectNoteScreenBody
import com.example.notesapp.ui.theme.primaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val pagerState = rememberPagerState (initialPage = 0) {
        2
    }
    val scope = rememberCoroutineScope()
    val onNextClick: () -> Unit = {
        scope.launch {
            pagerState.animateScrollToPage(1,
                animationSpec = tween(500)
            )
        }
    }
    val onExitClick: () -> Unit = {
        navController.navigate(Screens.HomeScreen.name)
    }
    val onSubmitClick: () -> Unit = {
        /*TODO*/
    }
    val outerContainerModifier = modifier
        .fillMaxSize()
        .background(color = primaryColor)
    Box(
        modifier = outerContainerModifier
    ) {
        HorizontalPager(state = pagerState) {
            when (it) {
                0 -> SelectNoteScreenBody(
                    onNextClick = onNextClick,
                    onExitClick = onExitClick
                )
                1 -> CreateNote(
                    onExitClick = onExitClick,
                    onSubmitClick =  onExitClick
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewAddNoteScreen() {
    AddNoteScreen(
        navController = rememberNavController()
    )
}