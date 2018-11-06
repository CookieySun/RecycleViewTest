package com.example.kktyu.recycleviewtest

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.row_title)
    val detailView: TextView = itemView.findViewById(R.id.row_detail)
}