package me.meenagopal24.notesxpert.ui.screens.notes

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.meenagopal24.notexpert.NotesXpert
import me.meenagopal24.notexpert.models.Note
import kotlin.time.ExperimentalTime

class NotesViewModel {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _notes = MutableStateFlow<List<Note>>(emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    val notes: StateFlow<List<Note>> = combine(_notes, _searchQuery) { notes, query ->
        if (query.isBlank()) notes.sortedBy { it.id }
        else notes.filter { note ->
            note.title.contains(query, ignoreCase = true) ||
                    note.body.contains(query, ignoreCase = true)
        }.sortedBy { it.id }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun loadNotes() {
        viewModelScope.launch {
            _notes.value = NotesXpert.getNotesUseCase()
        }
    }

    @OptIn(ExperimentalTime::class)
    fun addNote(note: Note) {
        viewModelScope.launch {
            _notes.update { currentNotes -> listOf(note) + currentNotes }
            NotesXpert.addNoteUseCase(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            _notes.update { currentNotes -> currentNotes.filter { it.id != note.id } }
            NotesXpert.deleteNoteUseCase(note.id)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            NotesXpert.updateNoteUseCase(note)
           loadNotes()
        }
    }

    fun addDummyNotes() {
        viewModelScope.launch {
            me.meenagopal24.notesxpert.dummy.notes.forEach {
                NotesXpert.addNoteUseCase(it)
            }
            loadNotes()
        }
    }
}
