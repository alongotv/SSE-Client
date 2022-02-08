package com.alongo.screenovatetest.data.networking.payment

import com.alongo.screenovatetest.common.API_ENDPOINT_URL
import com.alongo.screenovatetest.data.networking.SseClient
import com.alongo.screenovatetest.data.networking.SseMessage
import com.alongo.screenovatetest.domain.entity.Payment
import com.alongo.screenovatetest.domain.entity.dto.PaymentDto
import com.alongo.screenovatetest.utils.mapper.DtoDisplayMapper
import com.google.gson.Gson
import java.lang.IllegalArgumentException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import okhttp3.Request
import okhttp3.sse.EventSource

class PaymentApiImpl @Inject constructor(
    private val gson: Gson,
    private val sseFactory: EventSource.Factory,
    private val sseClient: SseClient,
    private val paymentDisplayMapper: DtoDisplayMapper<PaymentDto, Payment>
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
                    val paymentDto = gson.fromJson(message.data, PaymentDto::class.java)
                    paymentDisplayMapper.toDisplay(paymentDto)
                }
                else -> {
                    throw IllegalArgumentException("This flow only accepts Message objects ")
                }
            }
        }
    }
}
