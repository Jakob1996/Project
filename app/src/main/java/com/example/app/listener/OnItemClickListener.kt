package com.example.app.listener

import com.example.app.data.Item

interface OnItemClickListener {

    fun onItemClick(note: Item, position: Int)
}