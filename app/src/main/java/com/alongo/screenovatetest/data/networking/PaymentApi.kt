package com.alongo.screenovatetest.data.networking

import com.alongo.screenovatetest.domain.entity.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentApi {
    fun getPayments(): Flow<Payment>
}
