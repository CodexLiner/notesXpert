package me.meenagopal24.notesxpert.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import me.meenagopal24.notesxpert.ui.AppState
import me.meenagopal24.notesxpert.ui.components.HtmlContent
import me.meenagopal24.notexpert.models.Note
import notesxpert.app.composeapp.generated.resources.Res
import notesxpert.app.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    note: Note?,
    navController: NavHostController,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(text = note?.title.orEmpty(), maxLines = 1, overflow = TextOverflow.Ellipsis)
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
        })
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            HtmlContent(
                html = note?.body.orEmpty(), onLinkClick = { message ->
                    AppState.showToast(message)
                }
            )
        }
    }
}
