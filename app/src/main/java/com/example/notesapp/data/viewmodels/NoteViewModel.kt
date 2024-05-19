package com.example.notesapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.classes.Note
import com.example.notesapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NoteViewModelInterface"

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel(){
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val notesList = _noteList.asStateFlow()
    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect {
                        list ->
                    if (list.isEmpty()) {
                        Log.d("Empty", "Empty List")
                    }
                    else {
                        Log.d(TAG, list.toString())
                        _noteList.value = list
                    }
                }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }

        _noteList.value += note
    }

    fun getNote(id: String): Note {
        return notesList.value.first { note -> note.id.toString() == id }
    }

    suspend fun removeNote(note: Note) {
        viewModelScope.launch{
            repository.deleteNote(note)
        }
        _noteList.value -= note
    }

    suspend fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
        val index = _noteList.value.indexOf(note)
        if (index != -1) {
            _noteList.value[index].title = note.title
            _noteList.value[index].desc = note.desc
            _noteList.value[index].images = note.images
        }
    }
    fun removeAll() {
        viewModelScope.launch {
            repository.deleteAllNotes()
            _noteList.value = emptyList()
        }
    }


}