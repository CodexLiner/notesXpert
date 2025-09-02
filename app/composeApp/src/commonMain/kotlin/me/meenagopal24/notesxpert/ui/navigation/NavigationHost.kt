package me.meenagopal24.notesxpert.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.meenagopal24.notesxpert.ui.screens.details.NoteDetailScreen
import me.meenagopal24.notesxpert.ui.screens.notes.NotesListScreen
import me.meenagopal24.notexpert.NotesXpert

@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            NotesListScreen { noteId ->
                navController.navigate(Screens.NoteDetailScreen.createRoute(noteId))
            }
        }
        composable(
            Screens.NoteDetailScreen.route,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId")
            NoteDetailScreen(NotesXpert.getNoteByIdUseCase.invoke(noteId ?: 0) , navController)
        }
    }
}