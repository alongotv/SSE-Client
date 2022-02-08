package com.alongo.screenovatetest.data.networking

import com.alongo.screenovatetest.data.entity.error.UnknownException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import timber.log.Timber

class SseClient {

    val source = MutableStateFlow<SseMessage>(SseMessage.Closed)
    val clientScope = CoroutineScope(Dispatchers.IO)

    val eventSourceListener = object : EventSourceListener() {
        override fun onClosed(eventSource: EventSource) {
            super.onClosed(eventSource)
            clientScope.launch { source.emit(SseMessage.Open) }
            Timber.d("Event source closed ....")
        }

        override fun onEvent(
            eventSource: EventSource,
            id: String?,
            type: String?,
            data: String
        ) {
            super.onEvent(eventSource, id, type, data)
            clientScope.launch { source.emit(SseMessage.Message(data, type)) }
            Timber.d("Received data from source – id :$id, type: $type, data: $data")
        }

        override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
            super.onFailure(eventSource, t, response)
            val error = t ?: UnknownException()
            clientScope.launch { source.emit(SseMessage.Failure(error)) }
            Timber.e("$response: $t")
        }

        override fun onOpen(eventSource: EventSource, response: Response) {
            super.onOpen(eventSource, response)
            clientScope.launch { source.emit(SseMessage.Open) }
            Timber.d("Event source opened ....")
        }
    }
}
