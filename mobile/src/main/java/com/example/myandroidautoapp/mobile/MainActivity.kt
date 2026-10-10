package com.example.myandroidautoapp.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myandroidautoapp.shared.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = remember { SharedViewModel() }
            val uiState by viewModel.uiState.collectAsState()

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Mobile Companion", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(uiState.actions) { action ->
                        Text(text = "${action.title}: ${action.description}", modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }
    }
}