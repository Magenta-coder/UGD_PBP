package com.example.ugd3_pbp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_pbp.R
import com.example.ugd3_pbp.TambahEditObat
import com.example.ugd3_pbp.cariObat
import com.example.ugd3_pbp.model.obat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*
import kotlin.collections.ArrayList

class AdapterObat (private var obatList: List<obat>, context: Context):
    RecyclerView.Adapter<AdapterObat.ViewHolder>(), Filterable {

    private var filteredObatList: MutableList<obat>
    private val context: Context

    init{
        filteredObatList = ArrayList(obatList)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_obat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredObatList.size
    }

    fun setObatList(obatList: Array<obat>){
        this.obatList = obatList.toList()
        filteredObatList = obatList.toMutableList()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obat = filteredObatList[position]
        holder.tvNama.text = obat.nama
        holder.tvKode.text= obat.kode
        holder.tvJenis.text=obat.kode
        holder.tvTipe.text=obat.tipe

        holder.btnDelete.setOnClickListener{
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
            materialAlertDialogBuilder.setTitle("Konfirmasi")
                .setMessage("Apakah anda yakin ingin menghapus data obat ini?")
                .setNegativeButton("Batal",null)
                .setPositiveButton("Hapus"){_,_->
                    if(context is cariObat) obat.id?.let { it1 ->
                        context.deleteObat(
                            it1
                        )
                    }
                }
                .show()
        }
        holder.cvObat.setOnClickListener{
            val i = Intent(context, TambahEditObat::class.java)
            i.putExtra("id", obat.id)
            if(context is cariObat)
                context.startActivityForResult(i, cariObat.LAUNCH_ADD_ACTIVITY)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charSequenceString = charSequence.toString()
                val filtered: MutableList<obat> = java.util.ArrayList()
                if(charSequence.isEmpty()){
                    filtered.addAll(obatList)
                }else{
                    for(obat in obatList){
                        if(obat.nama.lowercase(Locale.getDefault())
                                .contains(charSequenceString.lowercase(Locale.getDefault()))
                        )filtered.add(obat)
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = filtered
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence, filterResult: FilterResults) {
                filteredObatList.clear()
                filteredObatList.addAll(filterResult.values as List<obat>)
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvNama: TextView
        var tvKode: TextView
        var tvJenis: TextView
        var tvTipe: TextView
        var btnDelete: ImageButton
        var cvObat: CardView
        init{
            tvNama = itemView.findViewById(R.id.tv_nama)
            tvKode = itemView.findViewById(R.id.tv_kode)
            tvTipe = itemView.findViewById(R.id.tv_tipe)
            tvJenis = itemView.findViewById(R.id.tv_jenis)
            btnDelete = itemView.findViewById(R.id.btn_delete)
            cvObat = itemView.findViewById(R.id.cv_obat)
        }
    }
}
