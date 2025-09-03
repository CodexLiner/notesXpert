package me.meenagopal24.notesxpert.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import me.meenagopal24.notesxpert.ui.AppState.hideToast
import me.meenagopal24.notesxpert.ui.AppState.toastState

@Composable
fun Toast() {
    val snackBarState = toastState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val showSnackbar = snackBarState.value.isNullOrEmpty().not()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        AnimatedVisibility(
            visible = showSnackbar,
            enter = slideInVertically(initialOffsetY = { -40 }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -40 }) + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { data ->
                    Snackbar(
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = snackBarState.value.orEmpty(),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            )
        }

        LaunchedEffect(snackBarState.value) {
            if (showSnackbar && snackBarState.value.isNullOrEmpty().not()) {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = snackBarState.value.orEmpty()
                    )
                    hideToast()
                }
            }
        }
    }
}