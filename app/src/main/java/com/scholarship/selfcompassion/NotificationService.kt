package com.scholarship.selfcompassion

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.scholarship.selfcompassion.AlarmService.RandomUtil.getRandomInt
import java.util.concurrent.atomic.AtomicInteger

class AlarmService(private val context: Context) {
    object RandomUtil {
        private val seed = AtomicInteger()
        fun getRandomInt() = seed.getAndIncrement() + System.currentTimeMillis().toInt()
    }


    private val alarmManager: AlarmManager? = context.getSystemService(
            Context.ALARM_SERVICE
    ) as AlarmManager?

    private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent) {
        alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY,
                pendingIntent)
    }

    fun getIntent() = Intent(context, AlarmReceiver::class.java)

    fun getPendingIntent(intent: Intent) = PendingIntent.getBroadcast(
            context,
            getRandomInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
    )

    fun setRepetitiveAlarm(timeInMillis: Long) {
        Log.i("[TRLOLO]Set alarm", " set alarm ")
        setAlarm(
                timeInMillis,
                getPendingIntent(
                        getIntent().apply {
                            action = "com.scholarship.selfcompassion"
                        }
                )
        )
    }
}