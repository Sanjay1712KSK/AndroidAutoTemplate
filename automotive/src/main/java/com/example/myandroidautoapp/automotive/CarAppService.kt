package com.example.myandroidautoapp.automotive

import android.content.Intent
import androidx.car.app.CarApplication
import androidx.car.app.CarAppService
import androidx.car.app.callback.ListTemplateCallback
import androidx.car.app.template.list.ListTemplate
import com.example.myandroidautoapp.shared.SharedViewModel

class CarAppService : CarApplication(), CarAppService {
    private lateinit var viewModel: SharedViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = SharedViewModel()
    }

    override fun onGetTemplate(data: Intent, availableModels: List<String>): ListTemplateCallback? {
        val actions = viewModel.uiState.value.actions
        
        return object : ListTemplateCallback() {
            override fun getCurrent(data: Intent): ListTemplate {
                val items = actions.map { action ->
                    ListTemplate.Item.builder()
                        .setTitle(action.title)
                        .setSummary(action.description)
                        .build()
                }
                
                return ListTemplate.builder()
                    .setTitle("Car App Actions")
                    .setItems(items)
                    .build()
            }

            override fun onNewIntent(data: Intent) {}
            override fun onAvailableModelsChanged(models: List<String>) {}
        }
    }
}