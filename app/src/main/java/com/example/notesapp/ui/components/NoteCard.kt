package com.example.notesapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.navigation.Screens
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.mohaveFamily
import com.example.notesapp.ui.theme.primaryColor
import java.util.UUID

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    title: String = "Title",
    desc: String,
    id: UUID,
    navController: NavController,
    type: Int
) {
    val screenType =
        if (type == 0) Screens.NoteDisplayScreen.name + "/$id/false"
        else Screens.DisplayVideo.name + "/$id"
    // Functions
    val onEditClick = {
        navController.navigate(screenType)
    }
    val onShowClick = {

        navController.navigate(screenType)
    }
    /*  Modifier Files */
    val boxModifier = modifier
        .width(134.dp)
        .height(IntrinsicSize.Min)
        .clip(RectangleShape)
        .border(
            width = 1.dp,
            color = Color.Black
        )
        .clickable { onShowClick.invoke() }
        .background(color = primaryColor)
    val titleRowModifier = modifier
        .fillMaxWidth()
        .border(
            width = 1.dp,
            color = Color.Black
        )
        .padding(horizontal = 4.dp)
    val descModifier = modifier
        .padding(4.dp)
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
    val editIconModifier = modifier
        .size(15.dp)



    /**Outermost container for the Note Card*/
    Box (
        modifier = boxModifier
    ) {
        Column (
            modifier = modifier.fillMaxSize()
        ) {
            // Row containing the title and the edit icon
            Row (
                modifier = titleRowModifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Title text
                CustomText(
                    modifier = modifier.fillMaxWidth(0.85f),
                    text = title,
                    textColor = Color.White,
                    fontFamily = modernFamily,
                    fontSize = 24)
                // Edit Icon
                IconButton(
                    modifier = editIconModifier,
                    onClick = onEditClick
                ) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                }
            }
            // Description Text
            CustomText(
                modifier = descModifier,
                text = desc,
                textColor = Color.White,
                fontFamily = mohaveFamily,
                fontSize = 18,
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview (showBackground = true)
@Composable
fun PreviewNoteCard() {
    NoteCard (
        desc = "",
        id = UUID.randomUUID(),
        navController = rememberNavController(),
        type = 0
    )
}