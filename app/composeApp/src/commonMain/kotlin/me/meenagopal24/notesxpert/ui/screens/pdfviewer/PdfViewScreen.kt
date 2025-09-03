package me.meenagopal24.notesxpert.ui.screens.pdfviewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import me.meenagopal24.notesxpert.ui.components.webview.WebView
import notesxpert.app.composeapp.generated.resources.Res
import notesxpert.app.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfViewScreen(navController: NavHostController, url: String, title: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
        }
        )
        Box(modifier = Modifier.weight(1f)) {
            val webUrl =
                if (url.startsWith("https://drive.google.com")) url else "https://drive.google.com/viewerng/viewer?embedded=true&url=$url"
            WebView(webUrl)
        }
    }
}