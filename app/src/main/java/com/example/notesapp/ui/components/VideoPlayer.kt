package com.example.notesapp.ui.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayer (
    modifier: Modifier = Modifier,
    videoUri: Uri,
    closePlayer: MutableState<Boolean>
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
        }
    }
    if (closePlayer.value) exoPlayer.release()
    AndroidView(
        factory = {
                ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }

        },
        modifier = modifier
    )
}