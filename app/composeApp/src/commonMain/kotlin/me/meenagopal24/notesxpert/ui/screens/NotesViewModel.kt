package me.meenagopal24.notesxpert.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.meenagopal24.notexpert.NotesXpert
import me.meenagopal24.notexpert.models.Note
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class NotesViewModel {
    private val _notes = mutableStateListOf<Note>()
    val notes: List<Note> get() = _notes

    var searchQuery by mutableStateOf("")
        private set


    val filteredNotes: List<Note>
        get() = if (searchQuery.isBlank()) _notes
        else _notes.filter {
            it.title.contains(searchQuery, ignoreCase = true) || it.body.contains(searchQuery, ignoreCase = true)
        }

    fun loadNotes() {
        _notes.addAll(NotesXpert.getNotesUseCase.invoke())
    }

    @OptIn(ExperimentalTime::class)
    fun addNote(title: String, body: String) {
        val newNote = Note(
            id = (_notes.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            body = body,
            createdAt = Clock.System.now().nanosecondsOfSecond
        )
        _notes.add(0, newNote)
    }

    fun deleteNote(note: Note) {
        _notes.remove(note)
        NotesXpert.deleteNoteUseCase(note.id)

    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
}