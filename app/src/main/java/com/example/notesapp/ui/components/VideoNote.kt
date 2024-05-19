package com.example.notesapp.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.AddBox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.data.classes.Note
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.ui.theme.mohaveFamily
import com.example.notesapp.ui.theme.primaryColor
import com.example.notesapp.ui.theme.ribeyeFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoNote (
    modifier: Modifier = Modifier,
    onExitClick: () -> Unit,
    notesViewModel: NoteViewModel
) {
    val context = LocalContext.current
    // Keyboard Options
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActions = KeyboardActions (
        onDone = {
            keyboardController?.hide()
        }
    )

    // State Holders
    val titleString = remember {
        mutableStateOf("Test")
    }
    val videoUri = remember {
        mutableStateOf<Uri?>(null)
    }


    val noUploadedVideo = remember (videoUri) {
        mutableStateOf(videoUri.value == null)
    }

    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            val packageName = context.packageName
            context.grantUriPermission(
                packageName,
                it,
                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            )
            videoUri.value = it
        })

    // functions
    val onUploadVideo = {
        videoPicker.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
        )
    }
    val onSubmit: () -> Unit = {
        if (titleString.value.isNotBlank() && videoUri.value != null) {
            val noteToBeAdded = Note(
                title = titleString.value,
                desc = "This is a video note. Click to view Video",
                images = listOf(videoUri.value!!),
                type = 1
            )
            notesViewModel.addNote(noteToBeAdded)
            onExitClick.invoke()
        }
        else Toast.makeText(context, "Add valid video", Toast.LENGTH_SHORT).show()
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
    val outerContainerModifier = modifier
        .fillMaxSize()
        .background(color = primaryColor)
    val textFieldModifier = modifier
        .width(263.dp)
        .background(
            color = Color.LightGray,
            shape = RoundedCornerShape(15.dp)
        )
        .clip(RoundedCornerShape(25.dp))

    Box(
        modifier = outerContainerModifier
    ){
        LazyColumn (
            modifier = cardModifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar for the card
            item {
                Row(
                    rowModifier
                        .height(40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(
                        text = "Notes",
                        textColor = Color.Black,
                        fontFamily = ribeyeFamily,
                        fontSize = 28
                    )
                    IconButton(onClick = onExitClick) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
            // Video Row to display the user uploaded video if they exist
            item {
                    Image(
                        modifier = imageModifier,
                        painter = painterResource(id = R.drawable.addnote),
                        contentDescription = null)
            }
            item {
                // The title heading and the text-field associated with it
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomText(
                        modifier = rowModifier.padding(horizontal = 40.dp),
                        text = "Title",
                        textColor = Color.Black,
                        fontFamily = ribeyeFamily,
                        fontSize = 25
                    )
                    BasicTextField2(
                        modifier = textFieldModifier
                            .height(48.dp)
                            .padding(14.dp),
//                        .focusRequester(focusRequester),
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
            }
            item{
                IconButton(onClick = onUploadVideo) {
                    Icon(imageVector = Icons.Filled.AddBox, contentDescription = null)
                }
            }
            item {
                Button(onClick = onSubmit) {
                    CustomText(
                        clickable = false,
                        text = "Submit",
                        textColor = Color.White,
                        fontFamily = mohaveFamily,
                        fontSize = 14)
                }
            }
        }
    }
}


