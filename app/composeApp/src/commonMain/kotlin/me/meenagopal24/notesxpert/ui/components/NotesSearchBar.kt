package me.meenagopal24.notesxpert.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import notesxpert.app.composeapp.generated.resources.Res
import notesxpert.app.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun NotesSearchBar(
    query: MutableState<String>,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
) {

    TextField(
        value = query.value,
        onValueChange = {
            onQueryChange(it)
        },
        placeholder = { Text("Search notes...") },
        leadingIcon = {
            Image(
                painterResource(Res.drawable.ic_search), contentDescription = "Search Icon"
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth().height(56.dp)
    )
}
