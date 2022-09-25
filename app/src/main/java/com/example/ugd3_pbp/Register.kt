package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ugd3_pbp.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText

class Register : AppCompatActivity() {

    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var date: TextInputEditText
    private lateinit var phoneNumber: TextInputEditText
    private lateinit var btnRegis: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("User Register")

        username = binding.username2
        password= binding.password2
        email = binding.email2
        date = binding.date2
        phoneNumber = binding.phone2
        btnRegis = binding.btnRegis


        val mBundle = Bundle()

        btnRegis.setOnClickListener(View.OnClickListener{

            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            mBundle.putString("email", email.text.toString())
            mBundle.putString("date", date.text.toString())
            mBundle.putString("phone", phoneNumber.text.toString())

            var checkLogin = false
            val checkUsername: String = username.text.toString()
            val checkPassword: String = password.text.toString()
            val checkPhoneNbr: String = phoneNumber.text.toString()
            val checkDate: String = date.text.toString()


            if (checkUsername.isEmpty()){
                username.setError("Username must be filled with text")
                checkLogin = false
            }else if (checkPassword.isEmpty()) {
                password.setError("Password must be filled with text")
                checkLogin = false
            }else if (checkPhoneNbr.isEmpty()) {
                phoneNumber.setError("Phone Number are required")
                checkLogin = false
            }else if(checkDate.isEmpty()) {
                date.setError("Date must be filled")
                checkLogin = false
            }else{
                checkLogin = true
            }

            if(!checkLogin)return@OnClickListener

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtras(mBundle)

            startActivity(intent)
        })
    }
}