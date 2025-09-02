package me.meenagopal24.notexpert.domain

import me.meenagopal24.notexpert.data.repository.NotesRepositoryImpl
import me.meenagopal24.notexpert.models.Note

class AddNoteUseCase() {
    private val repo = NotesRepositoryImpl()
    operator fun invoke(note: Note) = repo.addNote(note)
}