package com.dev.practical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.practical.R
import com.dev.practical.model.TaskModel
import kotlin.collections.ArrayList

class ChatListAdapter(val context: Context, val chatRoomList : ArrayList<TaskModel>, var onJoinSelectListener : OnSelectListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_room_list, parent, false)
        return chatListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatRoomList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is chatListViewHolder){
            val settingsViewHolder = holder

            val model = chatRoomList[position]
            settingsViewHolder.txTitle.text = model.taskTitle
            settingsViewHolder.txDescription.text = model.taskDescription
            settingsViewHolder.txRemindDate.text = model.remindMeDate
            settingsViewHolder.txDueDate.text = model.dueDate

            settingsViewHolder.itemView.setOnClickListener {
                onJoinSelectListener.onSelect(position)
            }
        }

    }

    inner class chatListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var txTitle = itemView.findViewById<TextView>(R.id.tx_title)
        var txDescription = itemView.findViewById<TextView>(R.id.tx_description)
        var txDueDate = itemView.findViewById<TextView>(R.id.tx_due_date)
        var txRemindDate = itemView.findViewById<TextView>(R.id.tx_remind_date)
    }

    interface OnSelectListener {
        fun onSelect(pos: Int)
    }
}