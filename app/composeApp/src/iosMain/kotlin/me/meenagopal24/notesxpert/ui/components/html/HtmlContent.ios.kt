package me.meenagopal24.notesxpert.ui.components.html

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
import platform.WebKit.WKNavigation
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKScriptMessage
import platform.WebKit.WKScriptMessageHandlerProtocol
import platform.WebKit.WKUserContentController
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.WebKit.javaScriptEnabled
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun HtmlContent(
    html: String,
    onLinkClick: (String) -> Unit,
) {
    val contentController = WKUserContentController()
    contentController.addScriptMessageHandler(
        object : NSObject(), WKScriptMessageHandlerProtocol {
            override fun userContentController(
                userContentController: WKUserContentController,
                didReceiveScriptMessage: WKScriptMessage,
            ) {
                onLinkClick(didReceiveScriptMessage.body.toString())
            }
        }, "JavaScriptBridge"
    )

    val config = WKWebViewConfiguration().apply {
        userContentController = contentController
        preferences.javaScriptEnabled = true
        preferences.javaScriptCanOpenWindowsAutomatically = true
    }

    var isLoading by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()) {
        UIKitView(
            factory = {
                WKWebView(
                    frame = CGRectMake(0.0, 0.0, 1000.0, 1000.0), configuration = config
                ).apply {
                    navigationDelegate = object : NSObject(), WKNavigationDelegateProtocol {
                        override fun webView(
                            webView: WKWebView,
                            didFinishNavigation: WKNavigation?,
                        ) {
                            isLoading = false
                        }
                    }

                    loadHTMLString(html, baseURL = null)
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