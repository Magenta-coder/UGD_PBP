package com.example.ugd3_pbp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.ugd3_pbp.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.*


class FragmentHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentDashboardBinding.inflate(layoutInflater)

        bind.buttonObat.setOnClickListener {
            val intent = Intent(this.requireContext(), TambahObat::class.java)
            startActivity(intent)
        }

        bind.floatingActionButton.setOnClickListener {
            val intent = Intent(this.requireContext(), PesanActivity::class.java)
            startActivity(intent)
        }

        return bind.root
        }


}