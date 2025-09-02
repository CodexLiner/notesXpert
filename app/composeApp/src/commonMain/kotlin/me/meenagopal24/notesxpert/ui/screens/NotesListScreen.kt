package me.meenagopal24.notesxpert.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.meenagopal24.notesxpert.ui.components.NotesSearchBar
import me.meenagopal24.notesxpert.ui.getRandomColor
import me.meenagopal24.notesxpert.ui.swipeToDelete
import me.meenagopal24.notexpert.models.Note
import notesxpert.app.composeapp.generated.resources.Res
import notesxpert.app.composeapp.generated.resources.ic_plus
import notesxpert.app.composeapp.generated.resources.ic_trash
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun NotesListScreen(viewModel: NotesViewModel = remember { NotesViewModel() }) {
    val notes by remember { derivedStateOf { viewModel.filteredNotes } }
    val searchQuery by remember { derivedStateOf { viewModel.searchQuery } }
    val coroutineScope = rememberCoroutineScope()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                NotesHeader(
                    statusBarHeight = statusBarHeight,
                    searchQuery = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) }
                )
            }

            items(notes, key = { it.id }) { note ->
                var dismissed by remember { mutableStateOf(false) }
                val noteColor = remember { getRandomColor() }

                AnimatedVisibility(
                    visible = !dismissed,
                    enter = expandVertically(animationSpec = tween(300)) + fadeIn(),
                    exit = shrinkVertically(animationSpec = tween(300)) + fadeOut()
                ) {
                    NoteCard(
                        note = note,
                        color = noteColor,
                        onDelete = {
                            dismissed = true
                            coroutineScope.launch { viewModel.deleteNote(note) }
                        },
                        onClick = { /* handle select note */ }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { viewModel.addNote("New Note", "Note content...") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = "Add Note"
            )
        }
    }
}

@Composable
private fun NotesHeader(
    statusBarHeight: Dp,
    searchQuery: String,
    onQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = statusBarHeight, bottom = 16.dp)
    ) {
        Text(
            text = "Notes",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
        NotesSearchBar(query = mutableStateOf(searchQuery), onQueryChange = onQueryChange)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteCard(
    note: Note,
    color: Color,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().swipeToDelete { onDelete() }.clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                color = Color.Black.copy(alpha = 0.8f)
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_trash),
                        contentDescription = "Delete Note",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

