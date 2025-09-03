package me.meenagopal24.notesxpert.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.meenagopal24.notesxpert.ui.screens.details.NoteDetailScreen
import me.meenagopal24.notesxpert.ui.screens.home.HomeScreen
import me.meenagopal24.notesxpert.ui.screens.notes.NotesListScreen
import me.meenagopal24.notesxpert.ui.screens.pdfs.PdfListScreen
import me.meenagopal24.notesxpert.ui.screens.pdfviewer.PdfViewScreen
import me.meenagopal24.notesxpert.utils.urlDecode
import me.meenagopal24.notexpert.NotesXpert

@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(Screens.PdfListScreen.route) {
            PdfListScreen(navController)
        }

        composable(
            Screens.PdfViewScreen.route,
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) {
            val url = it.arguments?.getString("url")?.urlDecode().orEmpty()
            val title = it.arguments?.getString("title").orEmpty()
            PdfViewScreen(navController, url , title)
        }

        composable(Screens.NotesListScreen.route) {
            NotesListScreen { noteId ->
                navController.navigate(Screens.NoteDetailScreen.createRoute(noteId))
            }
        }
        composable(
            Screens.NoteDetailScreen.route,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId")
            NoteDetailScreen(NotesXpert.getNoteByIdUseCase.invoke(noteId ?: 0), navController)
        }
    }
}