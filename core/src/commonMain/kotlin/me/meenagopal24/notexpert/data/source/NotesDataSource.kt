package me.meenagopal24.notexpert.data.source

import kotlinx.coroutines.flow.Flow
import me.meenagopal24.notesxpert.db.Notes

internal interface NotesDataSource {
    fun insertNote(note: Notes)
    fun deleteNote(id: Long)
    fun getNoteById(id: Long): Notes?
    fun observeNotes(): List<Notes>
}