package me.meenagopal24.notexpert.data.repository

import me.meenagopal24.notexpert.models.Note

internal interface NotesRepository {
    fun addNote(note: Note)
    fun deleteNote(id: Long)
    fun getNoteById(id: Long): Note?
    fun getAllNotes(): List<Note>

    fun updateNote(note: Note)
    fun deleteAllNotes()
}