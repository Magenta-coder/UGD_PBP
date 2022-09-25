package com.example.ugd3_pbp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd3_pbp.room.Constant
import com.example.ugd3_pbp.room.Obat
import com.example.ugd3_pbp.room.ObatDB
import kotlinx.android.synthetic.main.activity_tambah_obat.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TambahObat : AppCompatActivity() {

    val db by lazy { ObatDB(this) }
    lateinit var obatAdapter: ObatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_obat)
        setupListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        obatAdapter = ObatAdapter(arrayListOf(), object :
            ObatAdapter.OnAdapterListener{
            override fun onClick(obat: Obat) {
                intentEdit(obat.id,Constant.TYPE_READ)
            }

            override fun onUpdate(obat: Obat) {
                intentEdit(obat.id, Constant.TYPE_UPDATE)
            }

            override fun onDelete(obat: Obat) {
                deleteDialog(obat)
            }
        })

        list_note.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = obatAdapter }
    }

    private fun deleteDialog(obat: Obat){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Confirmation")
            setMessage("Are You Sure to Delete this data From " + "${obat.title}?")
            setNegativeButton("Cancel", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            setPositiveButton("Delete", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.obatDao().deleteObat(obat)
                    loadData()
                }
            })
        }
        alertDialog.show()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val obats = db.obatDao().getObat()
            Log.d("MainActivity","dbResponse: $obats")
            withContext(Dispatchers.Main){
                obatAdapter.setData( obats )
            }
        }
    }

    fun setupListener() {
        button_create.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(obatId : Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", obatId)
                .putExtra("intent_type", intentType)
        )
    }

}