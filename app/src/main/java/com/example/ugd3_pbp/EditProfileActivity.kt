package com.example.ugd3_pbp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ugd3_pbp.databinding.ActivityEditProfileBinding
import com.example.ugd3_pbp.databinding.ActivityProfileBinding
import com.example.ugd3_pbp.databinding.ActivityRegisterBinding
import com.example.ugd3_pbp.room.userDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EditProfileActivity : AppCompatActivity() {

    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var etEmail: EditText
    lateinit var etContactNo: EditText
    lateinit var etDes: EditText
    lateinit var img : ImageView
    val db by lazy { userDB(this) }
    private var bind: ActivityEditProfileBinding? = null
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        bind?.btnUpdateProfile?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        bind?.imageView4?.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

    }

}
