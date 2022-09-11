package com.example.ugd3_pbp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_pbp.entity.obat

class RVObatAdapter(private val data: Array<obat>) : RecyclerView.Adapter<RVObatAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_obat, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNamaObat.text = currentItem.name
        holder.tvHargaObat.text = currentItem.harga.toString()
        holder.tvGambarObat.setImageResource(currentItem.gambar)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaObat: TextView = itemView.findViewById(R.id.nama_obat)
        val tvHargaObat: TextView = itemView.findViewById(R.id.harga_obat)
        val tvGambarObat: ImageView = itemView.findViewById(R.id.gambar_obat)
    }
}