package me.meenagopal24.notesxpert.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import me.meenagopal24.notesxpert.ui.asHtml
import me.meenagopal24.notesxpert.ui.asLocalDateTime
import me.meenagopal24.notesxpert.ui.components.HtmlContent
import me.meenagopal24.notesxpert.ui.components.NotesSearchBar
import me.meenagopal24.notesxpert.ui.getRandomColor
import me.meenagopal24.notesxpert.ui.showable
import me.meenagopal24.notesxpert.ui.swipeToDelete
import me.meenagopal24.notesxpert.ui.toLocalDateTime
import me.meenagopal24.notexpert.models.Note
import notesxpert.app.composeapp.generated.resources.Res
import notesxpert.app.composeapp.generated.resources.ic_date
import notesxpert.app.composeapp.generated.resources.ic_edit
import notesxpert.app.composeapp.generated.resources.ic_plus
import notesxpert.app.composeapp.generated.resources.ic_trash
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(viewModel: NotesViewModel = remember { NotesViewModel() }) {
    val notes = viewModel.filteredNotes
    val searchQuery = viewModel.searchQuery
    val coroutineScope = rememberCoroutineScope()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    var showBottomSheet by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<Note?>(null) }

    LaunchedEffect(Unit) { viewModel.loadNotes() }

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 200.dp)
        ) {
            item {
                NotesHeader(
                    statusBarHeight = statusBarHeight,
                    searchQuery = searchQuery,
                    onQueryChange = viewModel::updateSearchQuery
                )
            }

            items(notes, key = { it.id }) { note ->
                var dismissed by remember { mutableStateOf(false) }
                val noteColor = remember { getRandomColor() }

                if (!dismissed) {
                    NoteCard(
                        modifier = Modifier.animateItem(),
                        note = note,
                        color = noteColor,
                        onDelete = {
                            dismissed = true
                            coroutineScope.launch { viewModel.deleteNote(note) }
                        },
                        onEdit = {
                            editingNote = note
                            showBottomSheet = true
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .navigationBarsPadding()
        ) {
            Icon(painter = painterResource(Res.drawable.ic_plus), contentDescription = "Add Note")
        }

        if (showBottomSheet) {
            AddNoteBottomSheet(
                note = editingNote,
                onSave = { note ->
                    editingNote?.let { viewModel.updateNote(it) } ?: viewModel.addNote(note)
                    editingNote = null
                    showBottomSheet = false
                }, onDismiss = {
                    showBottomSheet = false
                    editingNote = null
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
private fun AddNoteBottomSheet(
    note: Note? = null,
    onSave: (Note) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var title by remember { mutableStateOf(note?.title.orEmpty()) }
    var body by remember { mutableStateOf(note?.body.orEmpty()) }
    var creationDate by remember { mutableStateOf(note?.createdAt ?: Clock.System.now().toEpochMilliseconds()) }
    var showTitleError by remember { mutableStateOf(false) }
    var showBodyError by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = creationDate)

    val outlinedButtonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
    val outlinedButtonBorder = ButtonDefaults.outlinedButtonBorder.copy(
        brush = SolidColor(MaterialTheme.colorScheme.primary)
    )
    val roundedButtonShape = RoundedCornerShape(12.dp)
    val buttonModifier = Modifier.fillMaxWidth().height(56.dp)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        tonalElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .navigationBarsPadding()
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (note == null) "Add New Note" else "Edit Note",
                style = MaterialTheme.typography.headlineSmall
            )

            NoteInputField(title, { title = it }, "Title", showTitleError, 56.dp) { showTitleError = false }
            NoteInputField(body, { body = it }, "Body", showBodyError, 180.dp) { showBodyError = false }

            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = buttonModifier,
                shape = roundedButtonShape,
                colors = outlinedButtonColors,
                border = outlinedButtonBorder
            ) {
                Image(painter = painterResource(Res.drawable.ic_date), contentDescription = "Select Date")
                Spacer(Modifier.width(12.dp))
                Text(
                    text = datePickerState.selectedDateMillis
                        ?.let { Instant.fromEpochMilliseconds(it).toLocalDateTime().showable() }
                        ?: "Select Date",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    modifier = Modifier.padding(horizontal = 2.dp).fillMaxWidth(0.9f),
                    shape = roundedButtonShape,
                    tonalElevation = 8.dp,
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { creationDate = it }
                            showDatePicker = false
                        }) { Text("OK") }
                    },
                    dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } }
                ) {
                    DatePicker(showModeToggle = false, title = null, headline = null, state = datePickerState)
                }
            }

            Button(
                onClick = {
                    val validTitle = title.isNotBlank()
                    val validBody = body.isNotBlank()
                    showTitleError = !validTitle
                    showBodyError = !validBody

                    if (validTitle && validBody) {
                        onSave(Note(title = title, body = body, createdAt = creationDate, id = note?.id ?: 0))
                    }
                },
                modifier = buttonModifier,
                shape = roundedButtonShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(text = if (note == null) "Save" else "Update", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
private fun NoteInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
    height: Dp,
    onValid: () -> Unit,
) {
    Column {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                if (isError && it.isNotBlank()) onValid()
            },
            placeholder = { Text(placeholder) },
            singleLine = placeholder == "Title",
            maxLines = if (placeholder == "Title") 1 else 10,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
            modifier = Modifier.fillMaxWidth().height(height),
            isError = isError
        )
        if (isError) Text(
            "$placeholder is required",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}

@Composable
private fun NotesHeader(
    statusBarHeight: Dp,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = statusBarHeight, bottom = 16.dp)) {
        Text(
            "Notes",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
        NotesSearchBar(query = mutableStateOf(searchQuery), onQueryChange = onQueryChange)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    color: Color,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .swipeToDelete(onDelete),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp) , verticalArrangement = Arrangement.Center) {
            Text(note.title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                note.body.asHtml(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 20,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black.copy(alpha = 0.8f)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp).alpha(0.5f))
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.align(Alignment.BottomEnd) , verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = note.createdAt.asLocalDateTime().showable(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Icon(
                        painter = painterResource(Res.drawable.ic_edit),
                        contentDescription = "Edit Note",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onEdit() }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(Res.drawable.ic_trash),
                        contentDescription = "Delete Note",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onDelete() }
                    )
                }
            }
        }
    }
}
