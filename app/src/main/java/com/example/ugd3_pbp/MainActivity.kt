package com.example.ugd3_pbp

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.ugd3_pbp.room.userDB
import com.example.ugd3_pbp.room.userData
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var usernameLogin: TextInputEditText
    private lateinit var passwordLogin: TextInputEditText

    private lateinit var usernameLogin2: TextInputLayout
    private lateinit var passwordLogin2: TextInputLayout

//    lateinit var lBundle: Bundle

    lateinit var lUsername: String
    lateinit var lPassword: String

    val db by lazy { userDB(this) }

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


            CoroutineScope(Dispatchers.IO).launch {
                val user = db.userDao().getUser()
                var i = 1
                while (i < user.size) {
                    if (user[i].username.equals(username) && user[i].password.equals(password)) {
                        val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
                        startActivity(moveHome)
                    }
                    i++
                }
                Snackbar.make(btnLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show()

            }

        })
    }

}