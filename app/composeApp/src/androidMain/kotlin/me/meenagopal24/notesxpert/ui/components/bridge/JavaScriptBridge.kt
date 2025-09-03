package me.meenagopal24.notesxpert.ui.components.bridge

class JavaScriptBridge(
    private val onMessage: (String) -> Unit
) {
    @android.webkit.JavascriptInterface
    fun postMessage(msg: String) {
        onMessage(msg)
    }
}
