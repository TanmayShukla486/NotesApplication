package com.example.notesapp.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapp.data.classes.Note
import com.example.notesapp.utils.Converter


@Database (entities = [Note::class], version = 4, exportSchema = false)
@TypeConverters (Converter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}