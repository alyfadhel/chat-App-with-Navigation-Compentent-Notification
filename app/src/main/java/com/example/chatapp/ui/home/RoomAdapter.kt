package com.example.chatapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.dataBase.model.Room
import com.example.chatapp.databinding.LayoutRoomBinding

class RoomAdapter(var roomsList: List<Room>): RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: LayoutRoomBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.layout_room,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = roomsList[position]
        holder.bind(item)
        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, item)
            }
        }
    }

    var onItemClickListener: OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position: Int,room: Room)
    }

    class ViewHolder(val itemBinding: LayoutRoomBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(room: Room){
            itemBinding.vm = room
        }
    }
    fun changeData(roomsList: List<Room>){
        this.roomsList = roomsList
        notifyDataSetChanged()
    }
}