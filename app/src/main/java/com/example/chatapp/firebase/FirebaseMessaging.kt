package com.example.chatapp.firebase

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.chatapp.R
import com.example.chatapp.ui.Data
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessaging: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("token", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.notification?.imageUrl==null){
            showNotification(remoteMessage.notification?.title?:"", remoteMessage.notification?.body?:"")

        }else{
            Glide.with(this).asBitmap()
                .load(remoteMessage.notification?.imageUrl?:"")
                .addListener(object : RequestListener<Bitmap>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        showNotification( remoteMessage.notification?.title?:""
                            ,  remoteMessage.notification?.body?:"" , resource)
                        return true
                    }
                })

        }
    }
    fun showNotification(title: String, body: String){
        val notificationBuilder = NotificationCompat.Builder(this,Data.MESSAGES_NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(body)
        notificationBuilder.setSmallIcon(R.drawable.ic_message_notification)

        notificationBuilder.setStyle(NotificationCompat.BigTextStyle().bigText(body))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager
        notificationManager.notify(10,notificationBuilder.build())

    }
    fun showNotification(title: String, body: String,imageBitmap: Bitmap?){
        val notificationBuilder = NotificationCompat.Builder(this,Data.MESSAGES_NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(body)
        notificationBuilder.setSmallIcon(R.drawable.ic_message_notification)

        notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(imageBitmap))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.notify(10,notificationBuilder.build())

    }

}