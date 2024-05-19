package com.example.notesapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.notesapp.R
import com.example.notesapp.data.classes.Note
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.ui.components.CustomText
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.mohaveFamily
import com.example.notesapp.ui.theme.primaryColor
import com.example.notesapp.ui.theme.ribeyeFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicNoteScreen(
    modifier: Modifier = Modifier,
    onExitClick: () -> Unit,
    notesViewModel: NoteViewModel
) {

    // Keyboard Options
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActions = KeyboardActions (
        onDone = {
            keyboardController?.hide()
        }
    )
//    val focusRequester = remember {
//        FocusRequester()
//    }
    val context = LocalContext.current



    // state holders for the text-fields
    val titleString = remember {
        mutableStateOf("")
    }
    val descString = remember {
        mutableStateOf("")
    }
    val uriList = remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

//    LaunchedEffect(key1 = Unit) {
//        if (titleString.value.isBlank())
//            focusRequester.requestFocus()
//    }
    // media picker
    val multiplePhotoPicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = {
                uris -> uriList.value = uris.onEach {
                    val name = context.packageName
                    context.grantUriPermission(name, it, Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                    context.grantUriPermission(name, it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
                Log.d("Stack", uris.toString())
            }
        )

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
    val uploadedImageModifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
    val textFieldModifier = modifier
        .width(263.dp)
        .background(
            color = Color.LightGray,
            shape = RoundedCornerShape(15.dp)
        )
        .clip(RoundedCornerShape(25.dp))

    // functions for the buttons
    val onClick = {
        multiplePhotoPicker.launch (
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }
    val onSubmit = {
        Log.d("Create Note", "Add Note clicked")
        if (titleString.value.isNotBlank() && descString.value.isNotBlank()) {
            val noteToBeAdded = Note(
                title = titleString.value,
                desc = descString.value,
                type = 0
            )
            if (uriList.value.isNotEmpty()) {
                noteToBeAdded.images = uriList.value
            }
            notesViewModel.addNote(
                note = noteToBeAdded
            )
            titleString.value = ""
            descString.value = ""
        }
        onExitClick.invoke()
    }


    val noUploadedImage = remember {
        mutableStateOf(uriList.value.isEmpty())
    }

    val outerContainerModifier = modifier
        .fillMaxSize()
        .background(color = primaryColor)
    Box(
        modifier = outerContainerModifier
    ) {
        LazyColumn(
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
            // Image Row to display the user uploaded images if they exist
            item {
                if (noUploadedImage.value)
                    Image(
                        modifier = imageModifier,
                        painter = painterResource(id = R.drawable.addnote),
                        contentDescription = null
                    )
                else
                    LazyHorizontalStaggeredGrid(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .height(225.dp),
                        rows = StaggeredGridCells.Fixed(1),
                        horizontalItemSpacing = 8.dp
                    ) {
                        items(uriList.value) { uri ->
                            context.contentResolver.takePersistableUriPermission(
                                uri,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )
                            AsyncImage(
                                modifier = uploadedImageModifier,
                                model = uri,
                                contentDescription = null
                            )
                        }
                    }
            }
            // Input areas
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
                // The Body heading and the text-field associated with it
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomText(
                        modifier = rowModifier.padding(horizontal = 40.dp),
                        text = "Body",
                        textColor = Color.Black,
                        fontFamily = ribeyeFamily,
                        fontSize = 25
                    )
                    BasicTextField2(
                        modifier = textFieldModifier
                            .height(220.dp)
                            .padding(14.dp),
                        value = descString.value,
                        onValueChange = {
                            if (it.length <= 600)
                                descString.value = it
                        },
                        textStyle = TextStyle(
                            fontFamily = mohaveFamily,
                            color = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = keyboardActions
                    )
                }
            }
            // The bottom bar for the card
            item {
                Row(
                    modifier = rowModifier.padding(horizontal = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        CustomText(
                            text = "Image(s)",
                            textColor = Color.Black,
                            fontFamily = modernFamily,
                            fontSize = 14
                        )
                        IconButton(onClick = onClick) {
                            Icon(imageVector = Icons.Outlined.AddAPhoto, contentDescription = null)
                        }
                    }
                    Button(onClick = onSubmit) {
                        CustomText(
                            clickable = false,
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
}

@Preview (showBackground = true,
    backgroundColor = 4292894014)
@Composable
private fun PreviewCreateNote() {
    BasicNoteScreen(
        onExitClick = {},
        notesViewModel = viewModel()
    )
}