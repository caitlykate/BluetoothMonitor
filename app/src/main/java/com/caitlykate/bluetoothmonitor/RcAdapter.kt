package com.caitlykate.bluetoothmonitor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caitlykate.bluetoothmonitor.databinding.ListItemBinding

class RcAdapter(private val listener: Listener) : ListAdapter<ListItem, RcAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view){

        val binding = ListItemBinding.bind(view)

        fun setData(item: ListItem, listener: Listener) = with(binding){
            tvName.text = item.name
            tvMac.text = item.mac
            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }

        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.list_item, parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<ListItem>(){
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
             return oldItem.mac == newItem.mac
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

    }

    //создается
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    //заполняется
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    interface Listener{
        fun onClick(item: ListItem)
    }
}