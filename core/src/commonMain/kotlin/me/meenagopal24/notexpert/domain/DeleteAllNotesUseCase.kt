package me.meenagopal24.notexpert.domain

import me.meenagopal24.notexpert.data.repository.NotesRepositoryImpl

class DeleteAllNotesUseCase {
    private val repo = NotesRepositoryImpl()
    operator fun invoke() = repo.deleteAllNotes()
}