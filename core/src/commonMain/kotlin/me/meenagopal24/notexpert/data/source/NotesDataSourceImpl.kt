package me.meenagopal24.notexpert.data.source

import me.meenagopal24.notesxpert.db.Notes
import me.meenagopal24.notexpert.db.DatabaseDriverFactory
import me.meenagopal24.notexpert.db.LocalNotesDatabase

internal class NotesDataSourceImpl : NotesDataSource {
    private val database get() = LocalNotesDatabase(DatabaseDriverFactory().createDriver())
    private val queries get() = database.localNotesDatabaseQueries

    override fun insertNote(note: Notes) {
        queries.insertNote(
            title = note.title, body = note.body, createdAt = note.createdAt
        )
    }

    override fun deleteNote(id: Long) {
        queries.deleteNote(id)
    }

    override fun getNoteById(id: Long) = queries.getById(id).executeAsOneOrNull()

    override fun observeNotes() = queries.getAll().executeAsList()
}