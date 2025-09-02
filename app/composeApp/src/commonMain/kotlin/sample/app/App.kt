package sample.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.meenagopal24.notexpert.NotesXpert
import me.meenagopal24.notexpert.getFibonacciNumbers
import me.meenagopal24.notexpert.models.Note
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun App() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        val note = Note(
            title = "First Note",
            body = "This is the body of the first note",
            createdAt = Clock.System.now().nanosecondsOfSecond
        )

        print("ALLNOTES ${NotesXpert.getNotesUseCase()}")


        BasicText("getFibonacciNumbers(7)=${NotesXpert.getNotesUseCase()}")
    }
}