package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var usernameLogin: TextInputEditText
    private lateinit var passwordLogin: TextInputEditText

    private lateinit var usernameLogin2: TextInputLayout
    private lateinit var passwordLogin2: TextInputLayout

//    lateinit var lBundle: Bundle

    lateinit var lUsername: String
    lateinit var lPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("User Login")

        usernameLogin = findViewById(R.id.inputLayoutUsername3)
        passwordLogin = findViewById(R.id.inputLayoutPassword3)

        usernameLogin2 = findViewById(R.id.inputLayoutUsername2)
        passwordLogin2 = findViewById(R.id.inputLayoutPassword2)

        val lbundle = intent.extras
        usernameLogin.setText(lbundle?.getString("username"))
        passwordLogin.setText(lbundle?.getString("password"))

        lUsername = lbundle?.getString("username").toString()
        lPassword = lbundle?.getString("password").toString()

        val btnRegister = findViewById<Button>(R.id.btnRegister2)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener(View.OnClickListener {
            var checkLogin = false
            val username: String = usernameLogin2.getEditText()?.getText().toString()
            val password: String = passwordLogin2.getEditText()?.getText().toString()

            if (username.isEmpty()){
                usernameLogin2.setError("Username must be filled with text")
                checkLogin = false
            }

            if (password.isEmpty()){
                passwordLogin2.setError("Password must be filled with text")
                checkLogin = false
            }

            if (username == lUsername && password == lPassword) {
                checkLogin = true
            }else if (username != lUsername && password != lPassword){
                usernameLogin2.setError("Username Wrong")
                passwordLogin2.setError("Password Wrong")
                checkLogin = false
            }else if (username != lUsername){
                usernameLogin2.setError("Username Wrong")
                checkLogin = false
            }else{
                passwordLogin2.setError("Password Wrong")
                checkLogin = false
            }

            if(!checkLogin)return@OnClickListener


            val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(moveHome)

        })
    }

}