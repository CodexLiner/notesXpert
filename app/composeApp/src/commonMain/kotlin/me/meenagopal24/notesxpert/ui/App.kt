package me.meenagopal24.notesxpert.ui

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.meenagopal24.notesxpert.ui.screens.NotesListScreen
import me.meenagopal24.notexpert.NotesXpert
import me.meenagopal24.notexpert.models.Note
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        val note = Note(
            title = "First Note",
            body = "This is the body of the first note",
            createdAt = Clock.System.now().nanosecondsOfSecond
        )

        CompositionLocalProvider(
            LocalRippleConfiguration provides null
        ) {
            NotesListScreen(

            )
        }
    }
}