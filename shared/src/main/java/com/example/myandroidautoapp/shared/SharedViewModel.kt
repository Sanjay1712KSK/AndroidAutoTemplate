package com.example.myandroidautoapp.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidautoapp.shared.models.AppAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AppMode {
    MEDIA, MESSAGING, NAVIGATION
}

data class AppUiState(
    val actions: List<AppAction> = emptyList(),
    val currentMode: AppMode = AppMode.NAVIGATION
)

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        loadActions()
    }

    private fun loadActions() {
        val actions = mutableListOf<AppAction>()
        
        // Navigation Actions
        actions.add(AppAction("nav_1", "Start Navigation", "Navigate to your destination"))
        actions.add(AppAction("nav_2", "Home", "Go to home"))
        
        // Messaging Actions
        actions.add(AppAction("msg_1", "Send Message", "Quick reply to latest text"))
        
        // Media Actions
        actions.add(AppAction("media_1", "Play Music", "Play from playlist"))

        _uiState.value = AppUiState(actions = actions, currentMode = AppMode.NAVIGATION)
    }

    fun setMode(mode: AppMode) {
        _uiState.value = _uiState.value.copy(currentMode = mode)
    }

    fun performAction(actionId: String) {
        // Handle the action logic here
        println("Action performed: $actionId")
    }
}