package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ugd3_pbp.adapters.AdapterObat
import com.example.ugd3_pbp.api.ObatApi
import com.example.ugd3_pbp.model.obat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import org.json.JSONObject
import java.lang.Exception
import java.nio.charset.StandardCharsets

class cariObat : AppCompatActivity() {
    private var srObat: SwipeRefreshLayout?=null
    private var adapter:AdapterObat?=null
    private var svObat: SearchView? =null
    private var layoutLoading: LinearLayout? = null
    private var queue: RequestQueue? = null

    companion object{
        const val LAUNCH_ADD_ACTIVITY = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)
        layoutLoading = findViewById(R.id.layout_loading)
        srObat = findViewById(R.id.sr_obat)
        svObat = findViewById(R.id.sv_obat)

        srObat?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { allObat() })
        svObat?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter!!.filter.filter(s)
                return false
            }
        })

        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        fabAdd.setOnClickListener{
            val i = Intent(this@cariObat, TambahEditObat::class.java)
            startActivityForResult(i, LAUNCH_ADD_ACTIVITY)
        }
        val rvProduk= findViewById<RecyclerView>(R.id.rv_obat)
        adapter = AdapterObat(ArrayList(),this)
        rvProduk.layoutManager = LinearLayoutManager(this)
        rvProduk.adapter = adapter
        allObat()
    }

    private fun allObat(){
        srObat!!.isRefreshing = true
        val stringRequest = object :
            StringRequest(Method.GET, ObatApi.GET_ALL_URL, Response.Listener { response ->
                val gson = Gson()
                var obat:Array<obat> = gson.fromJson(response, Array<obat>::class.java)

                adapter!!.setObatList(obat)
                adapter!!.filter.filter(svObat!!.query)
                srObat!!.isRefreshing =false

                if(!obat.isEmpty())
                    Toast.makeText(this@cariObat, "Data Berhasil Diambil", Toast.LENGTH_SHORT)
                        .show()
                else
                    Toast.makeText(this@cariObat, "Data Kosong!", Toast.LENGTH_SHORT)
                        .show()
            }, Response.ErrorListener { error ->
                srObat!!.isRefreshing = false
                try{
                    val responseBody =
                        String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@cariObat,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                }catch (e: Exception){
                    Toast.makeText(this@cariObat,e.message, Toast.LENGTH_SHORT).show()
                }
            }){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue!!.add(stringRequest)
    }


    fun deleteObat(id:Long){
        setLoading(true)
        val stringRequest: StringRequest = object :
            StringRequest(Method.DELETE, ObatApi.DELETE_URL +id, Response.Listener { response ->
                setLoading(false)
                val gson = Gson()
                var obat = gson.fromJson(response, obat::class.java)
                if(obat != null)
                    Toast.makeText(this@cariObat, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                allObat()
            }, Response.ErrorListener { error ->
                setLoading(false)
                try{
                    val responseBody = String(error.networkResponse.data, StandardCharsets.UTF_8)
                    val errors = JSONObject(responseBody)
                    Toast.makeText(
                        this@cariObat,
                        errors.getString("message"),
                        Toast.LENGTH_SHORT).show()
                }catch (e: Exception){
                    Toast.makeText(this@cariObat, e.message, Toast.LENGTH_SHORT).show()
                }
            }){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = java.util.HashMap<String, String>()
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue!!.add(stringRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == LAUNCH_ADD_ACTIVITY && resultCode == RESULT_OK) allObat()
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
            layoutLoading!!.visibility = View.GONE
        }
    }
}