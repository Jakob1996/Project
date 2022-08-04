package com.example.app.data

import com.google.gson.annotations.SerializedName

data class SubInfoFirstItem(
    val id: String,
    @SerializedName("screenname")
    val screenName: String
)