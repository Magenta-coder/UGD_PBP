package com.example.ugd3_pbp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_pbp.room.Obat
import kotlinx.android.synthetic.main.activity_obat_adapter.view.*

class ObatAdapter (private val obats: ArrayList<Obat>, private val listener: OnAdapterListener) : RecyclerView.Adapter<ObatAdapter.ObatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatViewHolder {
        return ObatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_obat_adapter,parent, false)
        )
    }

    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        val obat = obats[position]
        holder.view.text_title.text = obat.title
        holder.view.text_title.setOnClickListener{
            listener.onClick(obat)
        }

        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(obat)
        }

        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(obat)
        }
    }

    override fun getItemCount() = obats.size

    inner class ObatViewHolder( val view: View) : RecyclerView.ViewHolder(view)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Obat>){
        obats.clear()
        obats.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(obat: Obat)
        fun onUpdate(obat: Obat)
        fun onDelete(obat: Obat)
    }

}