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

    val TAG = "foo"

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val httpRequest = Request.Builder()
        .url(API_ENDPOINT_URL + "payments" + "?limit=200&cursor=now")
        .addHeader("Accept", "text/event-stream")
        .get()
        .build()

    val sseFactory: EventSource.Factory = EventSources.createFactory(httpClient)
    val eventSourceListener = object : EventSourceListener() {

        override fun onClosed(eventSource: EventSource) {
            super.onClosed(eventSource)
            Log.d(TAG, "Event source closed ....")
        }

        override fun onEvent(
            eventSource: EventSource,
            id: String?,
            type: String?,
            data: String
        ) {
            super.onEvent(eventSource, id, type, data)
            Log.d(TAG, "id :$id, type: $type, data: $data")
        }

        override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
            super.onFailure(eventSource, t, response)
            Log.e(TAG, "$response $t")
        }

        override fun onOpen(eventSource: EventSource, response: Response) {
            super.onOpen(eventSource, response)
            Log.d(TAG, "Event source opened ....")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sseFactory.newEventSource(httpRequest, eventSourceListener)

    }
}