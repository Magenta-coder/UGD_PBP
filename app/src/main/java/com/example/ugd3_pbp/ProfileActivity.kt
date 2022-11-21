package com.example.ugd3_pbp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ugd3_pbp.databinding.ActivityProfileBinding
import com.example.ugd3_pbp.databinding.FragmentDashboardBinding
import com.example.ugd3_pbp.room.userDB
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileActivity : Fragment() {
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvTanggal: TextView

    lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = ActivityProfileBinding.inflate(layoutInflater)

        bind.profileIcon.setOnClickListener {
            val intent = Intent(this.requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }


//            preferences  = (activity as HomeActivity).getSharedPreferences()
//                (activity as HomeActivity).getSharedPreferences("prefId",Context.MODE_PRIVATE)

//            val username = preferences!!.getString("username","Coba")
//            val password = preferences!!.getString("password",null)

            val username = (activity as HomeActivity).getSharedPreferences()



                bind.username23.setText(username)
                bind.email2.setText(username)
                bind.phone2.setText(username)
                bind.tanggal2.setText(username)






        bind.btnEdit.setOnClickListener {
            val intent = Intent(this.requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }


}