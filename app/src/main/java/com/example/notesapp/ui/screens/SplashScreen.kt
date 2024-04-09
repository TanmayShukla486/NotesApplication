package com.example.notesapp.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.R
import com.example.notesapp.navigation.Screens

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    isFirstTime: Boolean
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                1200,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            ),
        )
        navController.popBackStack()
        navController.navigate(Screens.OnBoardingScreen.name)
    }

    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size((200 * scale.value).dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.notes_latest),
            contentDescription = null,

        )
    }
}