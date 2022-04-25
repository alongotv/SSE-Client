package com.alongo.ssetest.di.data.utils.mapper

import com.alongo.ssetest.domain.entity.Payment
import com.alongo.ssetest.domain.entity.dto.PaymentDataDto
import com.alongo.ssetest.utils.mapper.DtoDisplayMapper
import com.alongo.ssetest.utils.mapper.payment.PaymentDtoDisplayMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PaymentDtoDisplayMapperModule {
    @Provides
    fun providePaymentDtoDisplayMapper(): DtoDisplayMapper<PaymentDataDto, Payment> =
        PaymentDtoDisplayMapper()
}
