package com.example.ugd3_pbp

import android.content.Context
import android.content.Intent
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = ActivityProfileBinding.inflate(layoutInflater)




        CoroutineScope(Dispatchers.IO).launch {
            val sharedPreferences = (activity as HomeActivity).getSharedPreferences("prefId",Context.MODE_PRIVATE)

            val db by lazy { userDB(activity as HomeActivity) }
            val userDa = db.userDao()
            val user = userDa.getUser2(sharedPreferences!!.getInt("id",0))

            if (user != null) {
                bind.username23.setText(user.username)
                bind.email2.setText(user.email)
                bind.phone2.setText(user.phonenum)
                bind.tanggal2.setText(user.date)
            }

        }



        bind.btnEdit.setOnClickListener {
            val intent = Intent(this.requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }


}