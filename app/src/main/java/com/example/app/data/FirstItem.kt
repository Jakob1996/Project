package com.example.app.data

data class FirstItem(
    val explicit: Boolean,
    val has_more: Boolean,
    val limit: Int,
    val list: List<SubInfoFirstItem>,
    val page: Int,
    val total: Int
)