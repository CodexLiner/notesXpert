package me.meenagopal24.notexpert.domain

import me.meenagopal24.notexpert.data.repository.NotesRepositoryImpl

class GetNoteByIdUseCase {
    private val repo = NotesRepositoryImpl()
    operator fun invoke(id: Long) = repo.getNoteById(id)
}