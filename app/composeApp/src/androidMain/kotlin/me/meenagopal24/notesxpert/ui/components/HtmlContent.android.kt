package me.meenagopal24.notesxpert.ui.components

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun HtmlContent(html: String, onLinkClick: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.defaultTextEncodingName = "utf-8"

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        request: WebResourceRequest
                    ): Boolean {
                        onLinkClick(request.url.toString())
                        return true
                    }
                }
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(
                null,
                html,
                "text/html",
                "utf-8",
                null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp, max = 300.dp)
    )
}
