package com.example.ugd3_pbp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ugd3_pbp.api.UserApi
import com.example.ugd3_pbp.databinding.ActivityProfileBinding
import com.example.ugd3_pbp.databinding.FragmentDashboardBinding
import com.example.ugd3_pbp.room.userDB
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class ProfileActivity : Fragment() {
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvTanggal: TextView

    lateinit var preferences: SharedPreferences
    private var queue: RequestQueue? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = ActivityProfileBinding.inflate(layoutInflater)
        queue = Volley.newRequestQueue(this.context)

        bind.profileIcon.setOnClickListener {
            val intent = Intent(this.requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }


//            preferences  = (activity as HomeActivity).getSharedPreferences()
//                (activity as HomeActivity).getSharedPreferences("prefId",Context.MODE_PRIVATE)

//            val username = preferences!!.getString("username","Coba")
//            val password = preferences!!.getString("password",null)

            val id = (activity as HomeActivity).getSharedPreferences()





        val stringRequest: StringRequest = object : StringRequest(
            Method.GET, UserApi.TAMPIL_PROFILE_URL+id,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val resp = jsonObject.getString("server_response")
                    val resp2 = jsonObject.getJSONArray("server_response")

                    val jsn = resp2.getJSONObject(0)

                    bind.username23.setText(jsn.getString("username"))
                    bind.email2.setText(jsn.getString("email"))
                    bind.phone2.setText(jsn.getString("phonenum"))
                    bind.tanggal2.setText(jsn.getString("date"))


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { }) {

        }
        queue!!.add(stringRequest)






        bind.btnEdit.setOnClickListener {
            val intent = Intent(this.requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }


}