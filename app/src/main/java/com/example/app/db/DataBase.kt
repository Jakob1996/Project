package com.example.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.data.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters
abstract class DataBase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}