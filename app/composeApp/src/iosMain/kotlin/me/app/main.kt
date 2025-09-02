import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import me.meenagopal24.notesxpert.ui.App

fun MainViewController(): UIViewController = ComposeUIViewController { App() }