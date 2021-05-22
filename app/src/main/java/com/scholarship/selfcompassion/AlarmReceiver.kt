package com.scholarship.selfcompassion

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class AlarmReceiver: BroadcastReceiver() {
    companion object {
        private val CHANNEL_ID = "SelfCompassionDailyNotification"
        private val notificationID = 101
    }

    override  fun onReceive(context: Context, intent: Intent) {
        val sharedPref = context.getSharedPreferences("PromitData", Context.MODE_PRIVATE) ?: return
        Log.i("[TRLOLO]AlarmReceiver", " Received alarm, prepare notification")
        //setRepetitiveAlarm(AlarmService(context))
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            Log.i("[TRLOLO]AlarmReceiver", " BootMode")
            with (sharedPref.edit()) {
                putInt("notify", -1)
                apply()
            }
        } else {
            buildNotification(
                    context
            )
        }

    }

    private fun setRepetitiveAlarm(alarmService: AlarmService) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 10)
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        alarmService.setRepetitiveAlarm(calendar.timeInMillis)
    }

    private fun buildNotification(context: Context) {
        Log.i("[TRLOLO] buildN called", "send Notification")
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.notification_background)
        val intent: Intent = Intent(context, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.splash_logo)
                .setContentTitle("Buna ziua!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(bitmap)
                .setStyle(NotificationCompat.BigTextStyle().bigText("Va invitam sa completati " +
                        "exercitiul de autocompasiune de astazi!"))
                .setContentIntent(pendingIntent)

        with (NotificationManagerCompat.from(context)) {
            notify(notificationID, builder.build())
        }
    }
}