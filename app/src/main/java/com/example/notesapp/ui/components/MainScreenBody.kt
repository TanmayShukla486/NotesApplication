package com.example.notesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.R
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.ui.theme.mohaveFamily
import com.example.notesapp.ui.theme.primaryColor

@Composable
fun MainScreenBody(
    modifier: Modifier = Modifier,
    notesViewModel: NoteViewModel,
    navController: NavController
) {

    // modifiers
    val emptyColumnModifier = modifier.fillMaxSize()
    val card404Modifier = modifier
        .size(200.dp)
        .border(
            width = 5.dp,
            color = primaryColor,
            shape = RoundedCornerShape(10.dp)
        )
    val notesList = notesViewModel.notesList.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
            )
        if (notesList.value.isEmpty()) {
            Column (
                modifier = emptyColumnModifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    modifier = card404Modifier,
                    colors = CardDefaults.cardColors(
                        containerColor = primaryColor
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 25.dp
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = null)
                    CustomText(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "No Notes Found",
                        textColor = Color.White,
                        fontFamily = mohaveFamily,
                        fontSize = 20,
                        align = TextAlign.Center)
                }
            }
        }
        else {
            LazyVerticalStaggeredGrid(
                modifier = modifier.padding(16.dp),
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalItemSpacing = 25.dp
            ){
               items(notesList.value) {
                    note ->
                   NoteCard(
                       title = note.title,
                       desc = note.desc,
                       id = note.id,
                       navController = navController,
                       type = note.type
                   )
               }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewMainScreenBody() {
    MainScreenBody(
        notesViewModel = viewModel(),
        navController = rememberNavController()
    )
}