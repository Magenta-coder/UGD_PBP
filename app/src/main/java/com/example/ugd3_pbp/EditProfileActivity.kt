package com.example.ugd3_pbp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
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
    val db by lazy { userDB(this) }
    private var bind: ActivityEditProfileBinding? = null
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
//        viewInitializations()




//        userId = intent.getIntExtra("intent_id_profile", 0)
//        CoroutineScope(Dispatchers.IO).launch {
//            val user = db.userDao().getUser2(userId)
//            bind.usernameEdit.setText(user.username)
//        }

//        val lbundle = intent.extras
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//            val userDa = db.userDao()
//            val user = lbundle?.let { userDa.getUser2(it.getInt("idProfile")) }
//
//            if (user != null) {
//                bind!!.usernameEdit.setText(user.username)
//                bind!!.emailEdit.setText(user.email)
//                bind!!.phoneEdit.setText(user.phonenum)
//                bind!!.dateEdit.setText(user.date)
//            }
//
//        }
        bind?.btnUpdateProfile?.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }


//    fun viewInitializations() {
//
//        etFirstName = findViewById(R.id.et_first_name)
//        etLastName = findViewById(R.id.et_last_name)
//        etEmail  = findViewById(R.id.et_email)
//        etContactNo = findViewById(R.id.et_contact_no)
//        etDes = findViewById(R.id.et_des)
//
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//    }
//
//    fun validateInput(): Boolean {
//        if (etFirstName.text.toString().equals("")) {
//            etFirstName.setError("Please Enter First Name")
//            return false
//        }
//        if (etLastName.text.toString().equals("")) {
//            etLastName.setError("Please Enter Last Name")
//            return false
//        }
//        if (etEmail.text.toString().equals("")) {
//            etEmail.setError("Please Enter Email")
//            return false
//        }
//
//        if (etContactNo.text.toString().equals("")) {
//            etContactNo.setError("Please Enter Contact No")
//            return false
//        }
//        if (etDes.text.toString().equals("")) {
//            etDes.setError("Please Enter Designation")
//            return false
//        }
//        // checking the proper email format
//        if (!isEmailValid(etEmail.text.toString())) {
//            etEmail.setError("Please Enter Valid Email")
//            return false
//        }
//
//        return true
//    }
//
//    fun isEmailValid(email: String): Boolean {
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }
//
//
//    fun performEditProfile (view: View) {
//        if (validateInput()) {
//
//
//            val firstName = etFirstName.text.toString()
//            val lastName = etLastName.text.toString()
//            val email = etEmail.text.toString()
//            val contactNo = etContactNo.text.toString()
//            val etDes = etDes.text.toString()
//
//            Toast.makeText(this,"Profile Update Successfully",Toast.LENGTH_SHORT).show()
//
//        }
//    }
}
