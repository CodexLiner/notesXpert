package me.meenagopal24.notesxpert.ui.navigation

import kotlinx.serialization.Serializable
import me.meenagopal24.notexpert.models.Note

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object NoteDetailScreen : Screens("note_detail/{noteId}") {
        fun createRoute(noteId: String) = "note_detail/$noteId"
    }
}
