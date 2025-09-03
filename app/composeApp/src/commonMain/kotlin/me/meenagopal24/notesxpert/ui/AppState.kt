package me.meenagopal24.notesxpert.ui

import kotlinx.coroutines.flow.MutableStateFlow

object AppState {
    private var _toastState = MutableStateFlow<String?>(null)
    val toastState = _toastState

    fun showToast(message: String) {
        _toastState.value = message
    }

    fun hideToast() {
        _toastState.value = null
    }
}