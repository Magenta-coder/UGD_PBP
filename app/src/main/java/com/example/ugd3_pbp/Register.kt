package com.example.ugd3_pbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ugd3_pbp.databinding.ActivityRegisterBinding
import com.example.ugd3_pbp.room.Obat
import com.example.ugd3_pbp.room.ObatDB
import com.example.ugd3_pbp.room.userDB
import com.example.ugd3_pbp.room.userData
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {

    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var date: TextInputEditText
    private lateinit var phoneNumber: TextInputEditText
    private lateinit var btnRegis: Button

    val db by lazy { userDB(this) }
    private var obatId: Int = 0

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

                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().addUser(
                        userData(0,username.text.toString(),
                            email.text.toString(),date.text.toString(),phoneNumber.text.toString(),password.text.toString())
                    )
                    finish()
                }


            startActivity(intent)
        })
    }
}