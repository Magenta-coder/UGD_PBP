package com.example.ugd3_pbp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ugd3_pbp.databinding.ActivityPesanBinding

class PesanActivity : AppCompatActivity() {
    private var binding: ActivityPesanBinding? = null
    private val CHANNEL_ID = "channel_notification"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        createNotificationChannel()

        binding!!.btn1.setOnClickListener {
            sendNotification()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val notificationManager : NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setContentTitle(binding?.etTitle?.text.toString())
            .setContentText(binding?.etMessage?.text.toString())
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.text_dummy))
                .setBigContentTitle("Apoteker")
                .setSummaryText("Rangkuman Text"))
            .setPriority(NotificationCompat.PRIORITY_LOW)


        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }

    }
}