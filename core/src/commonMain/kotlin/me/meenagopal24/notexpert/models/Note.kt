package me.meenagopal24.notexpert.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long, val title: String, val body: String, val createdAt: Long,
)
