package com.example.lab08

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//new import
import android.view.View
import com.google.android.material.snackbar.Snackbar
import android.graphics.Color
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.os.Build
import android.app.Notification
import android.content.Context
import com.example.lab08.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setContentView(R.layout.activity_main)
    }
    fun mgSnack(view: View) {
        /*
        Snackbar.make(findViewById(R.id.sendMessage),
            "Your Email has been sent successfully",Snackbar.LENGTH_LONG)
            .setAction("OK"){}.setActionTextColor(Color.RED).show()
         */
        Snackbar.make(binding.root, "Your Email has been sent successfully", Snackbar.LENGTH_LONG).setAction("OK") {
            Toast.makeText(this, "Ok clicked", Toast.LENGTH_SHORT).show()
        }.setActionTextColor(Color.RED).show()
    }
    fun save(view: View){
        val saveAlert = AlertDialog.Builder(this)
        saveAlert.setTitle("Save")
        saveAlert.setMessage("Are you sure you want to save your changes?")
        saveAlert.setPositiveButton("Yes") { _, _ ->
            // Use binding to access the root view for Snackbar
            Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_LONG).show()
        }
        saveAlert.setNegativeButton("No") { _, _ ->
            Snackbar.make(binding.root, "Not Saved", Snackbar.LENGTH_LONG).show()
        }
        //saveAlert.setPositiveButton("Yes"){dialogInterface: DialogInterface, _: Int ->Snackbar.make( findViewById(R.id.showAlertDialog),"Saved",Snackbar.LENGTH_LONG).show()}
        //saveAlert.setNegativeButton("No"){dialogInterface: DialogInterface, _: Int ->Snackbar.make( findViewById(R.id.showAlertDialog),"Not Saved",Snackbar.LENGTH_LONG).show()}

        saveAlert.setNeutralButton("Remind me later", null)
        saveAlert.show()
    }
    fun courseupdate(view: View){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //create the notfication channel
            val channel_id = "channel_01"
            val channel_Name = "notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            /*
            val mChannel = NotificationChannel(channel_id, channel_Name,importance)
            mChannel.description = "test description"
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            */
            val notificationChannel = NotificationChannel(channel_id, channel_Name, importance).apply {
                description = "Test description"
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
            }

            //use notification.Builder to gtenerate yoru notification message
            /*
                val notification: Notification = Notification.Builder(this, channel_id).setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Android ATC Notification")
                .setContentText("Check Android ATC New Course !!").build()
            */
            val notification: Notification = Notification.Builder(this, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your own drawable
                .setContentTitle("Android ATC Notification")
                .setContentText("Check Android ATC New Course !!")
                .setAutoCancel(true) // Makes the notification dismissible when tapped
                .build()
            //register the channel with your android system
            val NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if(NotificationManager != null){
                NotificationManager.createNotificationChannel(notificationChannel)
                //Show the notification
                NotificationManager.notify(1, notification)
            }
        }
    }
}