package me.meenagopal24.notesxpert.ui

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random

fun Modifier.swipeToDelete(onDismissed: () -> Unit) = composed {
    pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            if (dragAmount > 200) {
                onDismissed()
            }
        }
    }
}


fun getRandomColor() = Color(
    Random.nextInt(200, 256),
    Random.nextInt(200, 256),
    Random.nextInt(200, 256)
)

fun Instant.toLocalDateTime(systemTimeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
    return toLocalDateTime(timeZone = systemTimeZone)
}

fun LocalDateTime.showable() = "${this.dayOfMonth.toString().padStart(2, '0')} " + "${this.month.name.lowercase().replaceFirstChar { it.uppercase() }} " + "${this.year}"
