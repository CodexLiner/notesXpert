package me.meenagopal24.notesxpert.ui

import androidx.compose.ui.text.AnnotatedString

actual fun String.asHtml(): AnnotatedString {
    val cleanedText = replace("<br>", "\n")
        .replace(Regex("<.*?>"), "")
    return AnnotatedString(cleanedText)
}