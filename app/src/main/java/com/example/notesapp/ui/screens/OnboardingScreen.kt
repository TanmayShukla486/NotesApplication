package com.example.notesapp.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.classes.onBoardingPages
import com.example.notesapp.navigation.Screens
import com.example.notesapp.ui.components.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState (initialPage = 0, pageCount = {3})
    val onNextClick: () -> Unit = {
            val nextPage = pagerState.currentPage + 1
            scope.launch {
                pagerState.animateScrollToPage(
                    page = nextPage.coerceAtMost(pagerState.pageCount - 1),
                    animationSpec = tween(
                        900,
                    )
                )
            }
    }
    val onSkipClick = {
        navController.popBackStack()
        navController.navigate(Screens.HomeScreen.name)
    }
    Column (
        modifier = modifier
            .background(color = Color(0xffF3725E))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            key = {
                index ->
                onBoardingPages[index].id
            }

        ) {
            OnBoardingPage(
                page = onBoardingPages[pagerState.currentPage],
                onNextClick = onNextClick,
                onSkipClick = onSkipClick,
                pageIndex = pagerState.currentPage
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewOnBoardingScreen() {
    OnBoardingScreen(navController = rememberNavController())
}