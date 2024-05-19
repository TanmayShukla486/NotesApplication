package com.example.notesapp.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.notesapp.R
import com.example.notesapp.data.viewmodels.NoteViewModel
import com.example.notesapp.ui.components.CustomText
import com.example.notesapp.ui.theme.mohaveFamily
import com.example.notesapp.ui.theme.primaryColor
import com.example.notesapp.ui.theme.ribeyeFamily
import com.example.notesapp.ui.theme.secondaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteDetailsScreen(
    modifier: Modifier = Modifier,
    isEditable: MutableState<Boolean>,
    noteViewModel: NoteViewModel,
    onGoBack: () -> Unit,
    id: String = ""
) {
    val context = LocalContext.current
    val note = remember {
        mutableStateOf(noteViewModel.getNote(id = id))
    }
    val scope = rememberCoroutineScope()

    Log.d("DEBUG", note.value.images.toString() + "is the string")




    // Modifier for each component
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
    val image404Modifier = modifier
        .size(251.dp, 163.dp)
        .clip(RoundedCornerShape(25.dp))
    val iconModifier = modifier
        .fillMaxSize()
//        .size(100.dp)
    val uploadedImageModifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))


    // Keyboard controls
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActions = KeyboardActions (
        onDone = {
            keyboardController?.hide()
        }
    )



    // media picker
    val multiplePhotoPicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris ->
                note.value.images += uris.onEach {
                    val name = context.packageName
                    context.grantUriPermission(
                        name,
                        it,
                        Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                    )
                    context.grantUriPermission(
                        name,
                        it,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                }
            }
        )

    // functions
    val onAddImages = {
        multiplePhotoPicker.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
        )
    }
    val onRemoveImages: () -> Unit = {
        note.value = note.value.copy(
            images = emptyList()
        )
        Log.d("DEBUG Note Images", note.value.images.toString())
    }
    val onUpdateClick: () -> Unit = {
        scope.launch {
            noteViewModel.updateNote(note.value)
        }
        onGoBack.invoke()
    }



    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        LazyColumn (
            modifier = columnModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        )  {
            item {
                Row (
                    modifier = rowModifier.padding(top = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(
                        text = "Images",
                        textColor = Color.White,
                        fontFamily = ribeyeFamily,
                        fontSize = 36)
                    IconButton(onClick = { isEditable.value = !isEditable.value }) {
                        if (!isEditable.value)
                            Icon(
                                imageVector = Icons.Filled.Create,
                                contentDescription = null,
                                tint = Color.White
                            )
                        else
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = null,
                                tint = Color.White
                            )
                    }
                }
            }
            item {
//                Log.d("IMAGES-TEST", note.value.images.size.toString())
                if (note.value.images.isEmpty() ||
                    (note.value.images.size == 1 && note.value.images[0].toString().isEmpty())
                ) {
                    Image(
                        modifier = image404Modifier,
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
                else {
                    LazyRow(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .height(200.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(note.value.images) {
                                image ->
                            context.contentResolver.takePersistableUriPermission(
                                image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )
                            AsyncImage(
                                modifier = uploadedImageModifier,
                                model = image,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            item {
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
                        keyboardActions = keyboardActions,
                        readOnly = !isEditable.value
                    )
                }
            }
            item {
                Row (
                    modifier = rowModifier
                ) {
                    CustomText(
                        text = "Body",
                        textColor = Color.White,
                        fontFamily = ribeyeFamily,
                        fontSize = 36
                    )
                }
            }
            item {
                Row (
                    modifier = modifier.padding(horizontal = 25.dp)
                ) {
                    BasicTextField2(
                        modifier = textFieldModifier
                            .height(250.dp)
                            .padding(14.dp),
                        value = note.value.desc,
                        onValueChange = {
                            note.value.desc = it
                        },
                        textStyle = TextStyle(
                            fontFamily = mohaveFamily,
                            color = Color.Black
                        ),
                        keyboardActions = keyboardActions,
                        readOnly = !isEditable.value
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
                    // Images
                    if (isEditable.value) {
                        IconButton(
                            onClick = onAddImages) {
                            Icon(
                                modifier = iconModifier,
                                imageVector = Icons.Filled.AddAPhoto,
                                contentDescription = "Add Images",
                                tint = Color.DarkGray
                            )
                        }
                        IconButton(
                            onClick = onRemoveImages) {
                            Icon(
                                modifier = iconModifier,
                                imageVector = Icons.Filled.HideImage,
                                contentDescription = "Remove Images",
                                tint = Color.DarkGray
                            )
                        }
                        IconButton(
                            onClick = onUpdateClick
                        ) {
                            Icon(
                                modifier = iconModifier,
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done",
                                tint = Color.DarkGray
                            )
                        }
                    }
                    // Displaying Go Back Button if Content is not editable
                    else {
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
                            onClick = onGoBack
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ImageRow(
    images: List<Uri>
) {
    val modifier = Modifier


}

@SuppressLint("UnrememberedMutableState")
@Preview (showBackground = true)
@Composable
fun PreviewNoteDetailScreen() {
    val viewModel: NoteViewModel = viewModel()
//    NoteDetailsScreen(
//        isEditable = mutableStateOf(true),
//        noteViewModel = viewModel,
//        onGoBack = {},
//        context = context,
//        permission = permission
//    )
}