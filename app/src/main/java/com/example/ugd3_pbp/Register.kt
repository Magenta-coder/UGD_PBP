package com.example.ugd3_pbp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

    private var bindings: ActivityRegisterBinding? = null
    private val CHANNEL_ID_1 = "channel_notification_01"
    private val CHANNEL_ID_2 = "channel_notification_02"
    private val notificationId1 = 101
    private val notificationId2 = 102

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

        createNotificationChannel()

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

            sendNotification1(username.text.toString())
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(CHANNEL_ID_1, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val channel2 = NotificationChannel(CHANNEL_ID_2, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val notificationManager : NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }

    private fun sendNotification1(name:String) {
        //Ini untuk ketika dipencent notifnya, dia ke main activity lagi
        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val broadcastIntent: Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", bindings?.username2?.text.toString())

        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_email_24)
            .setContentTitle("Berhasil Registrasi")
            .setContentText("Selamat Datang " + name)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.GREEN)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1, builder.build())
        }
    }

}