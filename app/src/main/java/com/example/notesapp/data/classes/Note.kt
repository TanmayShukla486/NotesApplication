package com.example.notesapp.data.classes

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    var title: String,
    @ColumnInfo(name = "note_body")
    var desc: String,
    @ColumnInfo(name = "note_images")
    var images: List<Uri> = emptyList(),
    @ColumnInfo(name = "note_type")
    val type: Int
)
