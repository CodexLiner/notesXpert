package me.meenagopal24.notesxpert.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun HtmlContent(html: String, onLinkClick: (String) -> Unit = {})