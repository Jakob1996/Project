package com.example.app.db

import android.content.Context
import androidx.room.Room

object DataBaseBuilder {

    private var instance: DataBase? = null

    fun getInstance(context: Context): DataBase {
        if (instance == null) {
            synchronized(DataBase::class) {
                instance = roomBuild(context)
            }
        }
        return instance!!
    }

    private fun roomBuild(context: Context) =
        Room.databaseBuilder(context.applicationContext, DataBase::class.java, "database")
            .allowMainThreadQueries().build()
}