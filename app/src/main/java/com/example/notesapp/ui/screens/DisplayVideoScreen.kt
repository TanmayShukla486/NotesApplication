package com.example.notesapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.ui.components.CustomText
import com.example.notesapp.ui.components.VideoPlayer
import com.example.notesapp.ui.theme.mohaveFamily
import com.example.notesapp.ui.theme.primaryColor
import com.example.notesapp.ui.theme.ribeyeFamily
import com.example.notesapp.ui.theme.secondaryColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayVideoScreen(
    modifier: Modifier = Modifier,
    id: String = "",
    noteViewModel: NoteViewModel,
    onBackClick: () -> Unit
) {
    val note = remember {
        mutableStateOf(noteViewModel.getNote(id = id))
    }
    val context = LocalContext.current
    val closePlayer = remember {
        mutableStateOf(false)
    }

    val columnModifier = modifier
        .fillMaxSize()
        .padding(35.dp)
        .background(primaryColor, shape = RoundedCornerShape(15.dp))
        .padding(vertical = 15.dp)
    val rowModifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 35.dp)
    val textFieldModifier = modifier
        .width(263.dp)
        .background(
            color = Color.White,
            shape = RoundedCornerShape(15.dp)
        )
        .clip(RoundedCornerShape(25.dp))

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            modifier = columnModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row(
                    modifier = rowModifier.padding(top = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(
                        text = "Video",
                        textColor = Color.White,
                        fontFamily = ribeyeFamily,
                        fontSize = 36
                    )
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
            item{
                context.contentResolver.takePersistableUriPermission(
                    note.value.images[0],
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                VideoPlayer(
                    modifier = modifier
                        .fillMaxWidth(0.85f)
                        .height(300.dp),
                    videoUri = note.value.images[0],
                    closePlayer = closePlayer
                )

            }
            item{
                Row (
                    modifier = modifier.padding(horizontal = 25.dp),
                ) {
                    CustomText(
                        text = "Title",
                        textColor = Color.White,
                        fontFamily = ribeyeFamily,
                        fontSize = 36
                    )
                }
                Row (
                    modifier = modifier.padding(horizontal = 25.dp),
                ) {
                    BasicTextField2(
                        modifier = textFieldModifier
                            .height(48.dp)
                            .padding(14.dp),
                        value = note.value.title,
                        onValueChange = {
                            if (it.length <= 32)
                                note.value.title = it
                        },
                        textStyle = TextStyle(
                            fontFamily = mohaveFamily,
                            color = Color.Black
                        ),
                        lineLimits = TextFieldLineLimits.SingleLine,
                        readOnly = true
                    )
                }
            }
            item {
                Row (
                    modifier = rowModifier.background(
                        color = secondaryColor,
                        shape = RoundedCornerShape(25.dp)
                    ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomText(
                        modifier = modifier
                            .height(32.dp)
                            .fillMaxWidth(),
                        text = "Go Back",
                        textColor = Color.Gray,
                        fontFamily = ribeyeFamily,
                        fontSize = 25,
                        align = TextAlign.Center,
                        clickable = true,
                        onClick = {
                            closePlayer.value = true
                            onBackClick.invoke()
                        }
                    )
                }
            }
        }
    }
}