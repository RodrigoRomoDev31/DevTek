package com.romvaz.core.data.location

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.romvaz.core.domain.api.weebhook.WebHookDataService
import com.romvaz.core.domain.location.LocationClientService
import com.romvaz.core.domain.models.api.requests.SendLocationPostModel
import com.romvaz.core.ui.R
import com.romvaz.core.ui.utils.DELAY_TIME_1000
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var locationClient: LocationClientService

    @Inject
    lateinit var webHookDataService: WebHookDataService

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.action?.let { action ->
            when (action) {
                ACTION_START -> start()
                ACTION_STOP -> stop()
            }
        }
        return START_NOT_STICKY
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(applicationContext.getString(R.string.tracking_location))
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setOngoing(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(NOTIFICATION_ID, notification.build())

        locationClient.getLocationUpdates(DELAY_TIME_1000)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val updatedNotification: NotificationCompat.Builder
                val result = webHookDataService.sendLocation(
                    SendLocationPostModel(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        timeStamp = location.time,
                        accuracy = location.accuracy,
                    )
                )

                updatedNotification = if (result.isSuccess)
                    notification.setContentText(applicationContext.getString(R.string.tracking_location_message))
                else
                    notification.setContentText(applicationContext.getString(R.string.problems_with_location))

                notificationManager.notify(NOTIFICATION_ID, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(NOTIFICATION_ID, notification.build())
    }

    private fun stop() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        serviceScope.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        private const val CHANNEL_ID = "location_channel"
        private const val CHANEL_NAME = "Location Tracking"
        private const val NOTIFICATION_ID = 1
    }
}
