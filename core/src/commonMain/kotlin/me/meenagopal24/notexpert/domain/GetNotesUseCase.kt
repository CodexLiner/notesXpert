package me.meenagopal24.notexpert.domain

import me.meenagopal24.notexpert.data.repository.NotesRepositoryImpl

class GetNotesUseCase() {
    private val repo = NotesRepositoryImpl()
    operator fun invoke()  = repo.observeNotes()
}