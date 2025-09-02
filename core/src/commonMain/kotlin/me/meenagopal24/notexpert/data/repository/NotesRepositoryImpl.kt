package me.meenagopal24.notexpert.data.repository

import me.meenagopal24.notexpert.data.source.NotesDataSourceImpl
import me.meenagopal24.notexpert.models.Note

class NotesRepositoryImpl : NotesRepository {
    private val dataSource = NotesDataSourceImpl()

    override fun addNote(note: Note) = dataSource.insertNote(me.meenagopal24.notesxpert.db.Notes(id = note.id, title = note.title, body = note.body, createdAt = note.createdAt))

    override fun deleteNote(id: Long) = dataSource.deleteNote(id)

    override fun getNoteById(id: Long) = dataSource.getNoteById(id)?.let {
        Note(id = it.id, title = it.title, body = it.body, createdAt = it.createdAt)
    }

    override fun observeNotes(): List<Note> = dataSource.observeNotes().map {
        Note(id = it.id, title = it.title, body = it.body, createdAt = it.createdAt)
    }

}