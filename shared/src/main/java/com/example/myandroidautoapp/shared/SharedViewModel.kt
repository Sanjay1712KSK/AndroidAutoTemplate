package com.example.myandroidautoapp.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidautoapp.shared.models.AppAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AppUiState(
    val actions: List<AppAction> = emptyList()
)

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        loadActions()
    }

    private fun loadActions() {
        val actions = listOf(
            AppAction("1", "Start Navigation", "Navigate to your destination"),
            AppAction("2", "Play Music", "Stream your favorite playlist"),
            AppAction("3", "Check Status", "View your current connection status")
        )
        _uiState.value = AppUiState(actions = actions)
    }

    fun performAction(actionId: String) {
        // Handle the action logic here
        println("Action performed: $actionId")
    }
}