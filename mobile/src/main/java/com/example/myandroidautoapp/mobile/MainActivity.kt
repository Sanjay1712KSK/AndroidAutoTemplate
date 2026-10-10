package com.example.myandroidautoapp.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myandroidautoapp.shared.SharedViewModel
import com.example.myandroidautoapp.shared.AppMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val viewModel = remember { SharedViewModel() }
                    val uiState by viewModel.uiState.collectAsState()

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Android Auto Master Template", style = MaterialTheme.typography.headlineMedium)
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Mode Switcher
                        Row(modifier = Modifier.padding(vertical = 8.dp)) {
                            AppMode.values().forEach { mode ->
                                Button(
                                    onClick = { viewModel.setMode(mode) },
                                    enabled = uiState.currentMode == mode,
                                    modifier = Modifier.padding(end = 4.dp)
                                ) {
                                    Text(text = mode.name)
                                }
                            }
                        }

                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(16.dp))

                        // Mode-Specific UI
                        when (uiState.currentMode) {
                            AppMode.NAVIGATION -> {
                                NavigationModeUI(uiState.actions)
                            }
                            AppMode.MEDIA -> {
                                MediaModeUI()
                            }
                            AppMode.MESSAGING -> {
                                MessagingModeUI()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationModeUI(actions: List<AppAction>) {
    Text("Navigation Mode", style = MaterialTheme.typography.titleLarge)
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn {
        items(actions) { action ->
            ListItem(
                headlineContent = { Text(action.title) },
                supportingContent = { Text(action.description) }
            )
        }
    }
}

@Composable
fun MediaModeUI() {
    Column {
        Text("Media Mode", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp)) {
            Text("Music Player UI (Media3 Integration Point)")
        }
    }
}

@Composable
fun MessagingModeUI() {
    Column {
        Text("Messaging Mode", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp)) {
            Text("Chat Interface UI (Messaging Library Integration Point)")
        }
    }
}