package me.meenagopal24.notesxpert.model

data class PdfItem(
    val url: String,
    val title: String = url.substringAfterLast("/"),
    val description: String = "Tap to view PDF"
)