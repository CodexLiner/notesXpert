package me.meenagopal24.notesxpert.ui.components.webview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKNavigation
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebView(url: String) {
    var isLoading by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()) {
        UIKitView(
            factory = {
                WKWebView(
                    frame = CGRectMake(0.0, 0.0, 1000.0, 1000.0),
                    configuration = WKWebViewConfiguration()
                ).apply {
                    navigationDelegate = object : NSObject(), WKNavigationDelegateProtocol {
                        override fun webView(
                            webView: WKWebView,
                            didFinishNavigation: WKNavigation?,
                        ) {
                            isLoading = false
                        }
                    }

                    loadRequest(NSURLRequest(NSURL(string = url)))
                }
            }, modifier = Modifier.fillMaxSize().background(Color.White)
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp).align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
