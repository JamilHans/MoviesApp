package com.trainingandroid.mobiedbapp.presentation.servicesfirebasecloudmessaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.trainingandroid.mobiedbapp.R
import com.trainingandroid.mobiedbapp.presentation.MainHostActivity


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            createNotification(it.title, it.body)
        }
    }

    override fun onNewToken(token: String) {
        println(token)
    }


    private fun createNotification(title: String?, messageBody: String?) {
        val intent = Intent(this, MainHostActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = getString(R.string.notification_default_channel_id)
        val notificationBuilder = createNotificationDefault(
            title,
            messageBody,
            channelId,
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
            pendingIntent
        )
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                createChannelDefault(
                    channelId,
                    getString(R.string.notification_default_channel_name),
                    getString(R.string.notification_default_channel_description)
                )
            )
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannelDefault(
        channelId: String,
        name: String,
        description: String
    ): NotificationChannel {
        return NotificationChannel(
            channelId,
            name,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            this.description = description
        }
    }

    private fun createNotificationDefault(
        title: String?,
        messageBody: String?,
        channelId: String,
        defaultSoundUri: Uri,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        return NotificationCompat
            .Builder(this, channelId)
            .apply {
                setSmallIcon(R.drawable.ic_launcher_background)
                setContentTitle(title)
                setContentText(messageBody)
                setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
                setAutoCancel(true)
                setSound(defaultSoundUri)
                setContentIntent(pendingIntent)
                priority = NotificationCompat.PRIORITY_DEFAULT
            }

    }
}
