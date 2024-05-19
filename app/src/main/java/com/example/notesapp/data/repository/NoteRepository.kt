package com.example.notesapp.data.repository

import com.example.notesapp.data.classes.Note
import com.example.notesapp.data.databases.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NoteRepository @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao
){
    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note)

    suspend fun getNoteById(id: String): Note = noteDatabaseDao.getNoteById(id)

    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()

    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)

    fun getAllNotes() : Flow<List<Note>> = noteDatabaseDao
        .getNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
}