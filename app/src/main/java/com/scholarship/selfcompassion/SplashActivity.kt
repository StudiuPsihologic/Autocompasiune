package com.scholarship.selfcompassion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    lateinit var alarmService: AlarmService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("PromitData", Context.MODE_PRIVATE) ?: return
        val default: Int = -1

        createNotificationChannel()
        if (sharedPref.getInt("notify", default) == -1) {
            Log.i("Main", " create the alarm")
            alarmService = AlarmService(this)
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 10)
            calendar[Calendar.MINUTE] = 0
            calendar[Calendar.SECOND] = 0
            alarmService.setRepetitiveAlarm(calendar.timeInMillis)
            with (sharedPref.edit()) {
                putInt("notify", 1)
                apply()
            }
        } else {
            Log.i("Main", " alarm already created")
        }



        val yesterdayDay = sharedPref.getInt("day", default)
        val month = sharedPref.getInt("month", default)
        val year = sharedPref.getInt("year", default)
        val dayId = sharedPref.getInt("day_id", 1)
        val activities = arrayOf(DayOneActivity::class.java, DayTwoActivity::class.java,
                        DayThreeActivity::class.java, DayFourActivity::class.java,
                        DayFiveActivity::class.java, DaySixActivity::class.java,
                        DaySevenActivity::class.java, DayEightActivity::class.java,
                        DayNineActivity::class.java)

        val calendarTomorrow = Calendar.getInstance()
        calendarTomorrow.time = Date()

        val calendarYesterday = Calendar.getInstance()

        if (year == -1 || month == -1 || yesterdayDay == -1) {
            Executors.newSingleThreadScheduledExecutor().schedule({
                startActivity(Intent(this,activities[dayId - 1]))
                finish()
            }, 3, TimeUnit.SECONDS)
        } else {

            calendarYesterday.set(year, month, yesterdayDay)

            if (calendarTomorrow.after(calendarYesterday)) {
            Executors.newSingleThreadScheduledExecutor().schedule({
                startActivity(Intent(this, activities[dayId - 1]))
                finish()
            }, 3, TimeUnit.SECONDS)
            } else {
                Executors.newSingleThreadScheduledExecutor().schedule({
                startActivity(Intent(this,RestDayActivity::class.java))
                finish()
                }, 3, TimeUnit.SECONDS)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification title"
            val descriptionText = "Notification description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("SelfCompassionDailyNotification", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
