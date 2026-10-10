package com.example.myandroidautoapp.shared.models

/**
 * Represents an action that can be performed within the app.
 * This is shared between the Mobile and Automotive UIs.
 */
data class AppAction(
    val id: String,
    val title: String,
    val description: String,
    val iconResId: Int? = null
)