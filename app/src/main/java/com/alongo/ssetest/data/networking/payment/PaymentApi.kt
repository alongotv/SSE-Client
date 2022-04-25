package com.alongo.ssetest.data.networking.payment

import com.alongo.ssetest.domain.entity.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentApi {
    suspend fun getPayments(): Flow<Payment>
}
