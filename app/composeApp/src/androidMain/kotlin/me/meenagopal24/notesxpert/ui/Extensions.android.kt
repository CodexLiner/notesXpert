package me.meenagopal24.notesxpert.ui

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.AnnotatedString

@RequiresApi(Build.VERSION_CODES.N)
actual fun String.asHtml(): AnnotatedString {
    val spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    return AnnotatedString(spanned.toString())
}