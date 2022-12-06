package com.example.ugd3_pbp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ugd3_pbp.api.UserApi
import com.example.ugd3_pbp.databinding.ActivityRegisterBinding
import com.example.ugd3_pbp.entity.User
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_edit.*
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class Register : AppCompatActivity() {

    private var username: TextInputEditText? = null
    private var password: TextInputEditText? = null
    private var email: TextInputEditText? = null
    private var date: TextInputEditText? = null
    private var phoneNumber: TextInputEditText? = null
    private var queue: RequestQueue? = null


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


        queue = Volley.newRequestQueue(this)
        username = binding.username2
        password= binding.password2
        email = binding.email2
        date = binding.date2
        phoneNumber = binding.phone2
        val btnRegis = binding.btnRegis

        val mBundle = Bundle()

        createNotificationChannel()

        btnRegis.setOnClickListener(View.OnClickListener{

            val sUsername = username!!.text.toString()
            val sEmail = email!!.text.toString()
            val sDate = date!!.text.toString()
            val sPhonenum = phoneNumber!!.text.toString()
            val sPassword: String = password!!.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtras(mBundle)
            if (sUsername.isEmpty()){
                StyleableToast.makeText(applicationContext, "Registrasi Gagal, Coba Lagi username", Toast.LENGTH_SHORT, R.style.mytoast).show()
            }else if (sPassword.isEmpty()){
                StyleableToast.makeText(applicationContext, "Registrasi Gagal, Coba Lagi Password", Toast.LENGTH_SHORT, R.style.mytoast).show()
            }
            else if (sEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()){
                StyleableToast.makeText(applicationContext, "Registrasi Gagal, Coba Lagi Email", Toast.LENGTH_SHORT, R.style.mytoast).show()
            }
            else if (sDate.isEmpty()){
                StyleableToast.makeText(applicationContext, "Registrasi Gagal, Coba Lagi Date", Toast.LENGTH_SHORT, R.style.mytoast).show()
            }
            else if (sPhonenum.isEmpty() || sPhonenum.length < 10){
                StyleableToast.makeText(applicationContext, "Registrasi Gagal, Coba Lagi Phone Number", Toast.LENGTH_SHORT, R.style.mytoast).show()
            }

            else{
                sendNotification1(sUsername)
                CreateDataToServer(sUsername,sEmail,sDate,sPhonenum,sPassword)
            }
//            startActivity(intent)
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

        val picture = BitmapFactory.decodeResource(resources, R.drawable.heart)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_email_24)
            .setContentTitle("Berhasil Registrasi")
            .setContentText("Selamat Datang " + name)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.GREEN)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Action", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigLargeIcon(null)
                .bigPicture(picture))

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1, builder.build())
        }
    }


    fun CreateDataToServer(username: String, email: String, date: String, phonenum: String, password: String) {
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST, UserApi.ADD_URL,
                Response.Listener { response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val resp = jsonObject.getString("server_response")
                        if (resp == "[{\"status\":\"OK\"}]") {
//                            Toast.makeText(
//                                applicationContext,
//                                "Registrasi Berhasil",
//                                Toast.LENGTH_SHORT
//                            ).show()
                            val dashboardIntent = Intent(this@Register, MainActivity::class.java)
                            StyleableToast.makeText(applicationContext, "Registrasi Berhasil", Toast.LENGTH_SHORT, R.style.mytoast2).show()
                            startActivity(dashboardIntent)
                        } else {
                            Toast.makeText(applicationContext, resp, Toast.LENGTH_SHORT).show()
//                            StyleableToast.makeText(applicationContext, "Registrasi Gagal, Coba Lagi", Toast.LENGTH_SHORT, R.style.mytoast).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String>? {
                    val params: MutableMap<String, String> = java.util.HashMap()
                    params["username"] = username
                    params["email"] = email
                    params["date"] = date
                    params["phonenum"] = phonenum
                    params["password"] = password
                    return params
                }
            }
        queue!!.add(stringRequest)

    }

}


