package com.alongo.ssetest.utils.mapper.payment

import com.alongo.ssetest.domain.entity.Payment
import com.alongo.ssetest.domain.entity.dto.PaymentDataDto
import com.alongo.ssetest.utils.mapper.DtoDisplayMapper

class PaymentDtoDisplayMapper : DtoDisplayMapper<PaymentDataDto, Payment>() {
    override fun toDisplay(obj: PaymentDataDto) =
        Payment(source = obj.source, destination = obj.destination, obj.amount)
}
