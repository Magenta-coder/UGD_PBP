package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ugd3_pbp.api.ObatApi
import com.example.ugd3_pbp.model.obat
import com.google.gson.Gson
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class TambahEditObat : AppCompatActivity() {
    companion object {
        private val JENIS_LIST = arrayOf("Cair", "Tablet", "Kapsul", "Tetes","Suppositoria","Inhaler")
        private val TIPE_LIST = arrayOf(
            "Resep Dokter",
            "Non-Resep Dokter",
        )
    }

    private var etNama: EditText? = null
    private var etKode: EditText? = null
    private var edJenis: AutoCompleteTextView? = null
    private var edTipe: AutoCompleteTextView? = null
    private var layoutLoading: LinearLayout? = null
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_edit_obat)

        //Deklarasi request queue

        queue = Volley.newRequestQueue(this)
        etNama = findViewById(R.id.et_nama)
        etKode = findViewById(R.id.et_kode)
        edJenis = findViewById(R.id.ed_jenis)
        edTipe= findViewById(R.id.ed_tipe)
        layoutLoading = findViewById(R.id.layout_loading)

        setExposedDropDownMenu()

        val btnCancel = findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener { finish() }
        val btnSave = findViewById<Button>(R.id.btn_save)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val id = intent.getLongExtra("id", -1)
        if (id == -1L) {
            tvTitle.setText("Tambah Obat")
            btnSave.setOnClickListener { createObat() }
        } else {
            tvTitle.setText("Edit Obat")
            getObatById(id)

            btnSave.setOnClickListener { updateObat(id) }
        }
    }

    fun setExposedDropDownMenu() {
        val adapterJenis: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.list_obat, JENIS_LIST)
        edJenis!!.setAdapter(adapterJenis)

        val adapterTipe: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.list_obat, TIPE_LIST)
        edTipe!!.setAdapter(adapterTipe)
    }

    private fun getObatById(id: Long) {
        setLoading(true)
        val stringRequest: StringRequest = object :
            StringRequest(
                Method.GET,
                ObatApi.GET_BY_ID_URL + id,
                Response.Listener { response ->
                    val gson = Gson()
                    val obat = gson.fromJson(response, obat::class.java)
                    etNama!!.setText(obat.nama)
                    etKode!!.setText(obat.kode)
                    edJenis!!.setText(obat.jenis)
                    edTipe!!.setText(obat.tipe)
                    setExposedDropDownMenu()

                    Toast.makeText(
                        this@TambahEditObat,
                        "Data berhasil diambil",
                        Toast.LENGTH_SHORT
                    ).show()
                    setLoading(false)
                },
                Response.ErrorListener { error ->
                    setLoading(false)
                    try {
                        val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                        val errors = JSONObject(responseBody)
                        Toast.makeText(
                            this@TambahEditObat,
                            errors.getString ("message"),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(this@TambahEditObat, e.message, Toast.LENGTH_SHORT).show()
                    }
                }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }

        }
        queue!!.add(stringRequest)
    }

    private fun createObat(){
        setLoading(true)

        val  mahasiswa = obat(
            etNama!!.text.toString(),
            etKode!!.text.toString(),
            edJenis!!.text.toString(),
            edTipe!!.text.toString(),
        )

        val stringRequest = object : StringRequest(Method.POST, ObatApi.ADD_URL, Response.Listener { response ->
            val gson = Gson()
            var obat = gson.fromJson(response, obat::class.java)

            if(obat != null)
                Toast.makeText(this@TambahEditObat, "Data Berhasil Ditambah", Toast.LENGTH_SHORT)
                    .show()

            val returnIntent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()

            setLoading(false)
        }, Response.ErrorListener { error ->
            setLoading(false)
            try {
                val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                val errors = JSONObject(responseBody)
                Toast.makeText(
                    this@TambahEditObat,
                    errors.getString("message" ),
                    Toast.LENGTH_SHORT
                ).show()
            }catch (e:Exception){
                Toast.makeText(this@TambahEditObat, e.message, Toast.LENGTH_SHORT).show()
            }
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "applications/json"
                return headers
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                val gson = Gson()
                val requestBody = gson.toJson(mahasiswa)
                return requestBody.toByteArray(StandardCharsets.UTF_8)
            }
            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        queue!!.add(stringRequest)
    }

    private fun updateObat(id: Long){
        setLoading(true)

        val obat = obat(
            etNama!!.text.toString(),
            etKode!!.text.toString(),
            edJenis!!.text.toString(),
            edTipe!!.text.toString(),
        )

        val stringRequest: StringRequest = object :
            StringRequest(Method.PUT, ObatApi.UPDATE_URL + id, Response.Listener { response ->
                val gson = Gson()

                var mahasiswa =gson.fromJson(response, ObatApi::class.java)

                if(mahasiswa != null)
                    Toast.makeText(this@TambahEditObat, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()

                val returnIntent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()

                setLoading(false)
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@TambahEditObat,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                }catch (e:Exception){
                    Toast.makeText(this@TambahEditObat, e.message, Toast.LENGTH_SHORT).show()
                }
            }){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                val gson = Gson()
                val requestBody = gson.toJson(obat)
                return requestBody.toByteArray(StandardCharsets.UTF_8)
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        queue!!.add(stringRequest)
    }


    private fun setLoading(isLoading: Boolean){
        if(isLoading){
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            layoutLoading!!.visibility = View.VISIBLE
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            layoutLoading!!.visibility = View.INVISIBLE
        }
    }
}