package com.example.notesapp.data.classes

import android.net.Uri
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var desc: String,
    var images: List<Uri> = emptyList()
)
