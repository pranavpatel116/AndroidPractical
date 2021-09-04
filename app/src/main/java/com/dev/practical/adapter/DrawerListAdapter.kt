package com.dev.practical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.practical.R
import com.dev.practical.model.DrawerMenuModel

class DrawerListAdapter(
    val context: Context,
    private val drawerList: ArrayList<DrawerMenuModel>,
    private val onDrawerItemSelectListener: OnDrawerItemSelectListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_drawer_menu, parent, false)
        return DrawerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drawerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DrawerViewHolder) {
            holder.txDrawer.text = drawerList[position].drawerName
            holder.ivDrawer.setImageResource(drawerList[position].drawerIcon)

            holder.itemView.setOnClickListener {
                onDrawerItemSelectListener.onDrawerItemSelect(position)
            }
        }
    }

    inner class DrawerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivDrawer: ImageView = itemView.findViewById(R.id.iv_drawer)
        var txDrawer: TextView = itemView.findViewById(R.id.tx_drawer)
    }

    interface OnDrawerItemSelectListener {
        fun onDrawerItemSelect(pos: Int)
    }
}