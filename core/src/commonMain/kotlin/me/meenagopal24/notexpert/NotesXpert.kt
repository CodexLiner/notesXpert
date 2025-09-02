package me.meenagopal24.notexpert

object NotesXpert {
    val addNoteUseCase by lazy {
        me.meenagopal24.notexpert.domain.AddNoteUseCase()
    }
    val deleteNoteUseCase by lazy {
        me.meenagopal24.notexpert.domain.DeleteNoteUseCase()
    }
    val getNoteByIdUseCase by lazy {
        me.meenagopal24.notexpert.domain.GetNoteByIdUseCase()
    }
    val getNotesUseCase by lazy {
        me.meenagopal24.notexpert.domain.GetNotesUseCase()
    }
}