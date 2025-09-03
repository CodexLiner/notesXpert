package me.meenagopal24.notesxpert.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

expect fun String.asHtml() : AnnotatedString


fun getRandomColor() = Color(
    Random.nextInt(200, 256),
    Random.nextInt(200, 256),
    Random.nextInt(200, 256)
)

fun Instant.toLocalDateTime(systemTimeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return toLocalDateTime(timeZone = systemTimeZone)
}
fun Long.asLocalDateTime(): LocalDateTime {
   return Instant.fromEpochMilliseconds(this).toLocalDateTime()

}
fun LocalDateTime.showable() = "${this.dayOfMonth.toString().padStart(2, '0')} " + "${this.month.name.lowercase().replaceFirstChar { it.uppercase() }} " + "${this.year}"
