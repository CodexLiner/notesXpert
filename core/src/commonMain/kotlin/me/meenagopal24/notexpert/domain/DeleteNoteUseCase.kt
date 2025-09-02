package me.meenagopal24.notexpert.domain

import me.meenagopal24.notexpert.data.repository.NotesRepositoryImpl

class DeleteNoteUseCase() {
    private val repo = NotesRepositoryImpl()
    operator fun invoke(id: Long) = repo.deleteNote(id)
}