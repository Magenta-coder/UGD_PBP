package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
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
        setContentView(R.layout.activity_register)

        setTitle("User Register")

        username = findViewById(R.id.username2)
        password= findViewById(R.id.password2)
        email = findViewById(R.id.email2)
        date = findViewById(R.id.date2)
        phoneNumber = findViewById(R.id.phone2)
        btnRegis = findViewById(R.id.btnRegis)


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

            if (checkUsername.isEmpty()){
                username.setError("Username must be filled with text")
                checkLogin = false
            }else if (checkPassword.isEmpty()){
                password.setError("Password must be filled with text")
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