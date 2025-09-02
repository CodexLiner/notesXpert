package me.meenagopal24.notexpert.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long = 0, val title: String, val body: String, val createdAt: Int,
)
