package me.meenagopal24.notesxpert.ui.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object PdfListScreen : Screens("pdf_list")
    object NotesListScreen : Screens("notes_list")

    object PdfViewScreen : Screens("pdf_view_screen/{url}/{title}") {
        fun createRoute(url: String, title: String) = "pdf_view_screen/$url/$title"
    }
    object NoteDetailScreen : Screens("note_detail/{noteId}") {
        fun createRoute(noteId: String) = "note_detail/$noteId"
    }
}
