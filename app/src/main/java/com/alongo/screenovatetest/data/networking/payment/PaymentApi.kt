package com.alongo.screenovatetest.data.networking.payment

import com.alongo.screenovatetest.domain.entity.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentApi {
    suspend fun getPayments(): Flow<Payment>
}
