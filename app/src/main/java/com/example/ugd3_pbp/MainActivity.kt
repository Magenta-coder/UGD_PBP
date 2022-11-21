package com.example.ugd3_pbp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ugd3_pbp.api.UserApi
import com.example.ugd3_pbp.room.userDB
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var usernameLogin: TextInputEditText
    private lateinit var passwordLogin: TextInputEditText

    private lateinit var usernameLogin2: TextInputLayout
    private lateinit var passwordLogin2: TextInputLayout
    private var queue: RequestQueue? = null

//    lateinit var lBundle: Bundle

    lateinit var lUsername: String
    lateinit var lPassword: String

    val db by lazy { userDB(this) }

    var pref: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("User Login")

        pref = getSharedPreferences("prefId", Context.MODE_PRIVATE)
        queue = Volley.newRequestQueue(this)

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
//            var checkLogin = false
            val username: String = usernameLogin2.getEditText()?.getText().toString()
            val password: String = passwordLogin2.getEditText()?.getText().toString()

//            if (username.isEmpty()){
//                usernameLogin2.setError("Username must be filled with text")
//                checkLogin = false
//            }
//
//            if (password.isEmpty()){
//                passwordLogin2.setError("Password must be filled with text")
//                checkLogin = false
//            }


            val editor= pref?.edit()

            editor?.putString("username",username)
            editor?.putString("password",password)
            editor?.apply()

            CheckLogin(username,password)



        })
    }

    fun CheckLogin(username: String, password: String) {
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST, UserApi.CHECK_LOGIN_URL,
                Response.Listener { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val resp = jsonObject.getString("server_response")
                        if (resp != "[{\"status\":\"FAILED\"}]") {

                            Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT)
                                .show()

                            val dashboardIntent = Intent(this@MainActivity, HomeActivity::class.java)
                            dashboardIntent.putExtra("usernameLogin",username);
                            startActivity(dashboardIntent)
                        } else {
                            Toast.makeText(applicationContext, "Login Gagal, Coba Lagi", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["username"] = username
                    params["password"] = password
                    return params
                }
            }
        queue!!.add(stringRequest)
    }

}