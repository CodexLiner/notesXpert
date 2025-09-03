package me.meenagopal24.notexpert.data.source

import me.meenagopal24.notesxpert.db.Notes

internal interface NotesDataSource {
    fun insertNote(note: Notes)
    fun deleteNote(id: Long)
    fun getNoteById(id: Long): Notes?
    fun getAllNotes(): List<Notes>
    fun updateNote(note: Notes)
}