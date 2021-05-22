package com.scholarship.selfcompassion

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import java.util.*

class DayEightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_eight)
        findViewById<WebView>(R.id.web_view_test).loadData(getString(R.string.day_eight_text),
            "text/html; charset=utf-8", "utf-8")
    }

    fun finishDayEight(view: View) {
        val c = Calendar.getInstance()
        c.time = Date()
        val sharedPref = getSharedPreferences("PromitData", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt("day", c.get(java.util.Calendar.DAY_OF_MONTH))
            putInt("month", c.get(java.util.Calendar.MONTH))
            putInt("year", c.get(java.util.Calendar.YEAR))
            putInt("promise_text", R.string.promise_day_eight)
            putInt("day_id", 9)
            apply()
        }

        setContentView(R.layout.activity_rest_day)
        var webView: WebView = findViewById<WebView>(R.id.rest_web_view)
        webView.setBackgroundResource(R.drawable.background)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.loadData(getString(R.string.promise_day_eight),"text/html; charset=utf-8", "utf-8")
    }
}