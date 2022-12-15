package com.example.ugd3_pbp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_pbp.databinding.FragmentDashboardBinding
import com.example.ugd3_pbp.databinding.FragmentObatBinding
import com.example.ugd3_pbp.entity.obat


class FragmentSearch : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentObatBinding.inflate(layoutInflater)

        bind.floatingActionButton2.setOnClickListener {
            val intent = Intent(this.requireContext(), TambahEditObat::class.java)
            startActivity(intent)
        }

        bind.FBMaps.setOnClickListener {
            val intent2 = Intent(this.requireContext(), MapsActivity::class.java)
            startActivity(intent2)
        }

        return bind.root
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter : RVObatAdapter = RVObatAdapter(obat.listOfObat)

        val rvObat : RecyclerView = view.findViewById(R.id.rv_obat)
        rvObat.layoutManager = layoutManager
        rvObat.setHasFixedSize(true)
        rvObat.adapter=adapter


    }

}