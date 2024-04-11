package com.example.notesapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.mohaveFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateNote(
    modifier: Modifier = Modifier,
    onExitClick: () -> Unit,
    onSubmitClick: () -> Unit
) {

    // Keyboard Options
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActions = KeyboardActions (
        onDone = {
            keyboardController?.hide()
        }
    )

    // state holders for the textfields
    val titleString = remember {
        mutableStateOf("")
    }
    val descString = remember {
        mutableStateOf("")
    }

    // Modifiers
    val cardModifier = modifier
        .fillMaxSize()
        .padding(25.dp)
        .clip(RoundedCornerShape(25.dp))
        .background(color = Color.White)
    val rowModifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
    val imageModifier = modifier.size(220.dp)
    val textFieldModifier = modifier
        .width(263.dp)
        .background(
            color = Color.LightGray,
            shape = RoundedCornerShape(15.dp)
        )
        .clip(RoundedCornerShape(25.dp))


    val noUploadedImage = true
    LazyColumn (
        modifier = cardModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null
                    )
                }
            }
        }
        item {
            if (noUploadedImage)
                Image(
                    modifier = imageModifier,
                    painter = painterResource(id = R.drawable.addnote),
                    contentDescription = null
                )
        }
        item {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CustomText(
                    modifier = rowModifier.padding(horizontal = 40.dp),
                    text = "Title",
                    textColor = Color.Black,
                    fontFamily = modernFamily,
                    fontSize = 25
                )
                BasicTextField2(
                    modifier = textFieldModifier
                        .height(48.dp)
                        .padding(14.dp),
                    value = titleString.value,
                    onValueChange = {
                        if (it.length <= 32)
                            titleString.value = it
                    },
                    textStyle = TextStyle(
                        fontFamily = mohaveFamily,
                        color = Color.Black
                    ),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    keyboardActions = keyboardActions
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    modifier = rowModifier.padding(horizontal = 40.dp),
                    text = "Body",
                    textColor = Color.Black,
                    fontFamily = modernFamily,
                    fontSize = 25
                )
                BasicTextField2(
                    modifier = textFieldModifier
                        .height(220.dp)
                        .padding(14.dp),
                    value = "Lorem ipsum doesasdajs ndbfdof doafladn f",
                    onValueChange = {

                    },
                    textStyle = TextStyle(
                        fontFamily = mohaveFamily,
                        color = Color.Black
                    ),
                    keyboardActions = keyboardActions
                )
            }
        }
        item {
            Row (
                modifier = rowModifier.padding(horizontal = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    CustomText(
                        text = "Image(s)",
                        textColor = Color.Black,
                        fontFamily = modernFamily,
                        fontSize = 14)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.AddAPhoto, contentDescription = null)
                    }
                }
                Button(onClick = { /*TODO*/ }) {
                    CustomText(
                        text = "Add Note",
                        textColor = Color.White,
                        fontFamily = mohaveFamily,
                        fontSize = 14
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true,
    backgroundColor = 4292894014)
@Composable
private fun PreviewCreateNote() {
    CreateNote(
        onExitClick = {},
        onSubmitClick = {}
    )
}