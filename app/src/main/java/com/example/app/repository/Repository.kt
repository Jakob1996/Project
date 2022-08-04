package com.example.app.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.app.data.Item
import com.example.app.data.SecondItem
import com.example.app.db.DataBaseBuilder
import com.example.app.retrofit.RetrofitClient

class Repository(app: Application) {

    private val roomBuilder = DataBaseBuilder.getInstance(app.applicationContext)
    private val itemDao = roomBuilder.itemDao()

    suspend fun getDailymotionItemsFromApi() =
        RetrofitClient.dailyMotionInstance
            .getFirstItems()
            .await()
            .body()!!

    suspend fun getGithubItemsFromApi(): ArrayList<SecondItem> =
        RetrofitClient.githubInstance
            .getSecondItems()
            .await()
            .body()!!

    suspend fun saveItem(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun getItems(): LiveData<List<Item>> =
        itemDao.getAllItems().asLiveData()
}