package com.alongo.ssetest.di.data.networking.payment

import com.alongo.ssetest.data.networking.payment.PaymentApi
import com.alongo.ssetest.data.networking.payment.PaymentApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentApiModule {
    @Binds
    abstract fun bindApiModule(paymentApiImpl: PaymentApiImpl): PaymentApi
}
