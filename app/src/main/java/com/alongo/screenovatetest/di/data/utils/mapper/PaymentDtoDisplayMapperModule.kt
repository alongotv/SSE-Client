package com.alongo.screenovatetest.di.data.utils.mapper

import com.alongo.screenovatetest.domain.entity.Payment
import com.alongo.screenovatetest.domain.entity.dto.PaymentDataDto
import com.alongo.screenovatetest.domain.entity.dto.PaymentDto
import com.alongo.screenovatetest.utils.mapper.DtoDisplayMapper
import com.alongo.screenovatetest.utils.mapper.payment.PaymentDtoDisplayMapper
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
