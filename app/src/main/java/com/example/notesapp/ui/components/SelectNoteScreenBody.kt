package com.example.notesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.mohaveFamily

@Composable
fun SelectNoteScreenBody(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit = {},
    onExitClick: () -> Unit = {}
) {
    val cardModifier = modifier
        .fillMaxSize()
        .padding(25.dp)
        .clip(RoundedCornerShape(25.dp))
        .background(color = Color.White)
    val rowModifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
    val imageModifier = modifier.size(220.dp)
    val notesOptions = listOf("Basic Note", "Audio Note", "Video Note")
    val selectedOption = remember {
        mutableStateOf(notesOptions[0])
    }
    Column(
        modifier = cardModifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            rowModifier
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(
                text = "Notes",
                textColor = Color.Black,
                fontFamily = modernFamily,
                fontSize = 28)
            IconButton(onClick = onExitClick) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = null
                )
            }
        }
        Image(
            modifier = imageModifier,
            painter = painterResource(id = R.drawable.selectnotes),
            contentDescription = null
        )
        CustomText(
            text = "Select Note Type",
            textColor = Color.Black,
            fontFamily = modernFamily,
            fontSize = 28
        )
        Column (
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            notesOptions.forEach { value ->
                Row (
                    modifier = rowModifier
                ) {
                    RadioButton(selected = value == selectedOption.value,
                        onClick = { selectedOption.value = value })
                    CustomText(
                        modifier = modifier.padding(top = 12.dp),
                        text = value,
                        textColor = Color.DarkGray,
                        fontFamily = mohaveFamily,
                        fontSize = 18)
                }
            }
        }
        Row (
            modifier = rowModifier.height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onNextClick) {
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true,
    backgroundColor = 4292894014)
@Composable
fun PreviewAddNoteScreenBody() {
    SelectNoteScreenBody()
}