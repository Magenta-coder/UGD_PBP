package com.example.ugd3_pbp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ugd3_pbp.room.Constant
import com.example.ugd3_pbp.room.Obat
import com.example.ugd3_pbp.room.ObatDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { ObatDB(this) }
    private var obatId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()
    }

    fun setupView(){
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getObat()
            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getObat()
            }
        }
    }

    private fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.obatDao().addObat(
                    Obat(0,edit_title.text.toString(),
                        edit_deskripsi.text.toString())
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.obatDao().updateObat(
                    Obat(obatId, edit_title.text.toString(),
                        edit_deskripsi.text.toString())
                )
                finish()
            }
        }
    }

    fun getObat() {
        obatId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val obats = db.obatDao().getObat(obatId)[0]
            edit_title.setText(obats.title)
            edit_deskripsi.setText(obats.description)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}