package me.meenagopal24.notesxpert.ui.components.html

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import me.meenagopal24.notesxpert.ui.components.html.bridge.JavaScriptBridge

@SuppressLint("SetJavaScriptEnabled")
@Composable
actual fun HtmlContent(html: String, onLinkClick: (String) -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.displayZoomControls = false
                    settings.textZoom = 100
                    settings.defaultTextEncodingName = "utf-8"
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    addJavascriptInterface(JavaScriptBridge { msg -> onLinkClick(msg) }, "JavaScriptBridge")
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView,
                            request: WebResourceRequest
                        ): Boolean {
                            onLinkClick(request.url.toString())
                            return true
                        }
                    }

                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            super.onProgressChanged(view, newProgress)
                            progress = newProgress / 100f
                        }
                    }
                }
            },
            update = { webView ->
                webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (progress < 1f) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(),
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        }
    }
}