package com.example.ugd3_pbp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.ugd3_pbp.databinding.FragmentDashboardBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*


class FragmentHome : Fragment() {
    private lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentDashboardBinding.inflate(layoutInflater)

        imageView = bind.imageViewDashboard

        Picasso.get().load("https://cdn.linkumkm.id/library/2/8/7/5/5/28755_840x576.jpg")
            .placeholder(R.drawable.load_page)
            .error(R.mipmap.ic_dashboard_image)
            .into(imageView)

        bind.floatingActionButton.setOnClickListener {
            val intent = Intent(this.requireContext(), PesanActivity::class.java)
            startActivity(intent)
        }

        bind.floatingActionButton3.setOnClickListener {
            val intent = Intent(this.requireContext(), PdfOrQRActivity::class.java)
            startActivity(intent)
        }

        return bind.root
        }


}