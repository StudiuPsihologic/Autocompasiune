package com.scholarship.selfcompassion

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class RestDayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_day)

        val sharedPref = getSharedPreferences("PromitData", Context.MODE_PRIVATE) ?: return
        val promise_id = sharedPref.getInt("promise_text", R.string.promise_day_one)

        var webView: WebView = findViewById<WebView>(R.id.rest_web_view)
        webView.setBackgroundResource(R.drawable.background)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.loadData(getString(promise_id),"text/html; charset=utf-8", "utf-8")
    }
}