package com.example.notesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.primaryColor

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    title: String = "Title",
    onEditClick: () -> Unit = {},
    onClick: () -> Unit,
    content: @Composable() (() -> Unit)
) {
    val boxModifier = modifier
        .width(134.dp)
        .height(IntrinsicSize.Min)
        .clip(RectangleShape)
        .border(
            width = 1.dp,
            color = Color.Black
        )
        .clickable { onClick.invoke() }
        .background(color = primaryColor)
    val titleRowModifier = modifier
        .fillMaxWidth()
        .border(
            width = 1.dp,
            color = Color.Black
        )
        .padding(horizontal = 4.dp)
    val editIconModifier = modifier
        .size(15.dp)
    Box (
        modifier = boxModifier
    ) {
        Column (
            modifier = modifier.fillMaxSize()
        ) {
            Row (
                modifier = titleRowModifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomText(
                    text = title,
                    textColor = Color.Black,
                    fontFamily = modernFamily,
                    fontSize = 20)
                IconButton(
                    modifier = editIconModifier,
                    onClick = onEditClick
                ) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                }
            }
            content()
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewNoteCard() {
    NoteCard (
        onClick = {},
        content = {
            Row (Modifier.height(150.dp)) {

            }
        }
    )
}