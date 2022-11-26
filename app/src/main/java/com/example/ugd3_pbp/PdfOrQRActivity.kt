package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ugd3_pbp.databinding.ActivityPdfOrQractivityBinding
import com.example.ugd3_pbp.databinding.ActivityRegisterBinding
import com.squareup.picasso.Picasso

class PdfOrQRActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private var bindings: ActivityPdfOrQractivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPdfOrQractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = binding.iVPDForQR

        Picasso.get().load("https://img.freepik.com/premium-vector/woman-sitting-table-with-laptop-working-computer-freelance-online-education-social-media-concept-working-from-home-remote-job-flat-style-illustration_186332-28.jpg?w=2000")
            .placeholder(R.drawable.load_page)
            .error(R.mipmap.ic_dashboard_image)
            .into(imageView)

        binding.floatingActionButton4.setOnClickListener {
            val dashboardIntent = Intent(this@PdfOrQRActivity, HomeActivity::class.java)
            startActivity(dashboardIntent)
        }

    }
}