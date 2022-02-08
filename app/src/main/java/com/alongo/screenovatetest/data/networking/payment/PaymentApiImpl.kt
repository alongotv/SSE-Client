package com.alongo.screenovatetest.data.networking.payment

import com.alongo.screenovatetest.common.API_ENDPOINT_URL
import com.alongo.screenovatetest.data.networking.SseClient
import com.alongo.screenovatetest.data.networking.SseMessage
import com.alongo.screenovatetest.domain.entity.Payment
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import okhttp3.Request
import okhttp3.sse.EventSource

class PaymentApiImpl @Inject constructor(
    private val sseFactory: EventSource.Factory,
    private val sseClient: SseClient
) : PaymentApi {

    private val httpRequest = Request.Builder()
        .url(API_ENDPOINT_URL + "payments" + "?limit=200&cursor=now")
        .addHeader("Accept", "text/event-stream")
        .get()
        .build()

    override suspend fun getPayments(): Flow<Payment> {
        sseFactory.newEventSource(httpRequest, sseClient.eventSourceListener)

        return sseClient.source.filter {
            it is SseMessage.Message
        }.map { message ->
            when (message) {
                is SseMessage.Message -> {
                    Payment(message.data, "calabria", 10.0)
                }
                else -> {
                    throw IllegalArgumentException("This flow only accepts Message objects ")
                }
            }
        }
    }
}
