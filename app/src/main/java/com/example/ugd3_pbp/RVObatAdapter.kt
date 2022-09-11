package com.example.ugd3_pbp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_pbp.entity.obat

class RVObatAdapter(private val data: Array<obat>) : RecyclerView.Adapter<RVObatAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_obat, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNama.text = currentItem.name
        holder.tvJumlah.text = "${currentItem.jumlah}"
    }



    override fun getItemCount(): Int {
//        Disini kita memberitahu jumlah dari item pada recycler view kita
        return data.size
    }

    // Kelas ini berguna untuk menghubungkan view view yang ada pada item recycler view kita
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNama : TextView = itemView.findViewById(R.id.tv_nama_obat)
        val tvJumlah : TextView = itemView.findViewById(R.id.tv_jumlah)

    }

}