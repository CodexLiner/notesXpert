package me.meenagopal24.notesxpert.ui.components.html.bridge

import android.webkit.JavascriptInterface

class JavaScriptBridge(
    private val onMessage: (String) -> Unit
) {
    @JavascriptInterface
    fun postMessage(msg: String) {
        onMessage(msg)
    }
}
