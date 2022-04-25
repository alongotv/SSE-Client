package com.alongo.ssetest.data.networking.payment

import com.alongo.ssetest.common.API_ENDPOINT_URL
import com.alongo.ssetest.data.networking.SseClient
import com.alongo.ssetest.data.networking.SseMessage
import com.alongo.ssetest.domain.entity.Payment
import com.alongo.ssetest.domain.entity.dto.PaymentDataDto
import com.alongo.ssetest.utils.mapper.DtoDisplayMapper
import com.google.gson.Gson
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
    private val paymentDisplayMapper: DtoDisplayMapper<PaymentDataDto, Payment>
) : PaymentApi {

    private val httpRequest = Request.Builder()
        .url(API_ENDPOINT_URL + "payments" + "?limit=200&cursor=now")
        .addHeader("Accept", "text/event-stream")
        .get()
        .build()

    override suspend fun getPayments(): Flow<Payment> {
        sseFactory.newEventSource(httpRequest, sseClient.eventSourceListener)

        return sseClient.source.filter {
            // Makes sure no messages other than data payloads pass through
            it is SseMessage.Message && it.type == null
        }.map { message ->
            when (message) {
                is SseMessage.Message -> {
                    val paymentDto = gson.fromJson(message.data, PaymentDataDto::class.java)
                    paymentDisplayMapper.toDisplay(paymentDto)
                }
                else -> {
                    throw IllegalArgumentException("This flow only accepts Message objects ")
                }
            }
        }
    }
}
