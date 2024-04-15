package com.example.apimvvmunitdemo.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker

import androidx.work.WorkerParameters
import com.example.apimvvmunitdemo.R
import com.example.apimvvmunitdemo.ui.MainActivity
import com.example.apimvvmunitdemo.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.example.apimvvmunitdemo.utils.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.apimvvmunitdemo.utils.Constants.NOTIFICATION_CONTENT_TEXT
import com.example.apimvvmunitdemo.utils.Constants.NOTIFICATION_CONTENT_TITLE
import com.example.apimvvmunitdemo.utils.Constants.NOTIFICATION_ID
import com.example.apimvvmunitdemo.utils.Constants.NOTIFICATION_ID_KEY


import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/*
@HiltWorker
class NewsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
  // private val databaseService: DatabaseService,
    private val networkService: NetworkService
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        lateinit var result: Result
        kotlin.runCatching {
            val articles = networkService.getTopHeadlines().articles.map { article ->
              //  article.toArticleEntity()
            }
         //   databaseService.deleteAllInsertAll(articles)
        }.onSuccess {
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)) {
                sendNotification()
            }
            result = Result.success()
        }.onFailure {
            result = Result.retry()
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        // create an intent for the MainActivity
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(NOTIFICATION_ID_KEY, NOTIFICATION_ID)

        // create PendingIntent
        val pendingIntent = getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // build notification
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.def)
            .setContentText(NOTIFICATION_CONTENT_TEXT)
            .setContentTitle(NOTIFICATION_CONTENT_TITLE)
            .setContentIntent(pendingIntent)
            .build()

        // show notification
        notificationManager.notify(NOTIFICATION_ID, notification)

    }

}*/
