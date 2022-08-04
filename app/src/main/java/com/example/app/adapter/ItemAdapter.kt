package com.example.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.data.Item
import com.example.app.databinding.ItemBinding
import com.example.app.listener.OnItemClickListener

class ItemAdapter(private val itemList: List<Item>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.bind(item)
    }

    override fun getItemCount() = itemList.size

    inner class ViewHolder(private val viewBinding: ItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.itemCl.setOnClickListener {
                listener.onItemClick(itemList[adapterPosition], adapterPosition)
            }
        }

        fun bind(item: Item) {
            viewBinding.run {
                itemUsername.text = item.login
                itemImage.text = item.avatar_url
                itemApiName.text = item.url
            }
        }
    }
}