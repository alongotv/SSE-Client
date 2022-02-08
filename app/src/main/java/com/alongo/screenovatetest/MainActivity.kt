package com.alongo.screenovatetest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alongo.screenovatetest.common.API_ENDPOINT_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

class MainActivity : AppCompatActivity() {

    val httpRequest = Request.Builder()
        .url(API_ENDPOINT_URL + "payments" + "?limit=200&cursor=now")
        .addHeader("Accept", "text/event-stream")
        .get()
        .build()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sseFactory.newEventSource(httpRequest, eventSourceListener)
    }
}