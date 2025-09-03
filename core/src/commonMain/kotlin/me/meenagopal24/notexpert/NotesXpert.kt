package me.meenagopal24.notexpert

import me.meenagopal24.notexpert.domain.*

object NotesXpert {
    val addNoteUseCase by lazy {
        AddNoteUseCase()
    }
    val deleteNoteUseCase by lazy {
        DeleteNoteUseCase()
    }
    val getNoteByIdUseCase by lazy {
        GetNoteByIdUseCase()
    }
    val getNotesUseCase by lazy {
        GetNotesUseCase()
    }

    val updateNoteUseCase by lazy {
        UpdateNoteUseCase()
    }
    val deleteAllNotesUseCase by lazy {
        DeleteAllNotesUseCase()
    }
}