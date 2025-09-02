package me.meenagopal24.notesxpert.ui

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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