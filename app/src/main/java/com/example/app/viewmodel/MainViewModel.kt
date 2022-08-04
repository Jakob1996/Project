package com.example.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.app.repository.Repository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    val repository = Repository(app)

    suspend fun getFirstItems() = repository.getDailymotionItemsFromApi()

    suspend fun getSecondItems() = repository.getGithubItemsFromApi()

    suspend fun getAllItems() = repository.getItems()
}
