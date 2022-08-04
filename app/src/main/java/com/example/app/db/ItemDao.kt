package com.example.app.db

import androidx.room.*
import com.example.app.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(note: Item)

    @Query("SELECT*FROM ITEM_TABLE ORDER BY rowId DESC")
    fun getAllItems(): Flow<List<Item>>
}