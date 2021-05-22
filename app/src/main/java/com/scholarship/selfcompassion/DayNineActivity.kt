package com.scholarship.selfcompassion

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class DayNineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_nine)
        findViewById<WebView>(R.id.web_view_test).loadData(getString(R.string.day_nine_text),
            "text/html; charset=utf-8", "utf-8")
    }

    fun finishDayNine(view: View) {
        val c = Calendar.getInstance()
        c.time = Date()
        val sharedPref = getSharedPreferences("PromitData", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt("day", c.get(java.util.Calendar.DAY_OF_MONTH))
            putInt("month", c.get(java.util.Calendar.MONTH))
            putInt("year", c.get(java.util.Calendar.YEAR))
            putInt("promise_text", R.string.promise_day_nine)
            putInt("day_id", 1)
            apply()
        }

        setContentView(R.layout.activity_rest_day)
        var webView: WebView = findViewById<WebView>(R.id.rest_web_view)
        webView.setBackgroundResource(R.drawable.background)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.loadData(getString(R.string.promise_day_nine),"text/html; charset=utf-8", "utf-8")
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://forms.gle/1S6gKH9dphgRypoU9")
        startActivity(openURL)
    }
}