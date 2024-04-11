package com.example.notesapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.navigation.Screens
import com.example.notesapp.ui.components.MainScreenBody
import com.example.notesapp.ui.components.TopNavBar
import com.example.notesapp.ui.theme.secondaryColor

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    notesList: List<String> = emptyList(),
    navController: NavController,
    onClickNewNote: () -> Unit = {}
) {
    val iconModifier = modifier
        .size(50.dp)
        .background(secondaryColor)
        .border(width = 3.dp, color = Color.Black, shape = CircleShape)
    val buttonModifier = modifier
        .size(50.dp)
        .clip(CircleShape)
        .shadow(
            elevation = 25.dp,
            ambientColor = Color.DarkGray,
            spotColor = Color.Black
        )
    Scaffold (
        topBar = {
            TopNavBar(
                title = "Notes",
                screen = Screens.HomeScreen
            )
        },
        floatingActionButton = {
            IconButton(
                modifier = buttonModifier,
                onClick = onClickNewNote
            ) {
                Icon(
                    modifier = iconModifier,
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Surface (
            modifier = modifier.padding(paddingValues = it)
        ) {
            MainScreenBody()
        }
    }
}


@Preview (showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(navController = rememberNavController())
}