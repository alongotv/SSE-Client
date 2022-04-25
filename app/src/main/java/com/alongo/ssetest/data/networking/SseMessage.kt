package com.alongo.ssetest.data.networking

sealed class SseMessage {
    object Open : SseMessage()
    object Closed : SseMessage()
    data class Message(val data: String, val type: String?) : SseMessage()
    data class Failure(val error: Throwable) : SseMessage()
}
