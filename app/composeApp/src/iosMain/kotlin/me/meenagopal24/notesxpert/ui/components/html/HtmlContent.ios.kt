package me.meenagopal24.notesxpert.ui.components.html

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
actual fun HtmlContent(html: String, onLinkClick: (String) -> Unit) {
    Text(
        text = html,
        softWrap = true,
    )
}