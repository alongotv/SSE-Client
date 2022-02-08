package com.alongo.screenovatetest.utils.mapper.payment

import com.alongo.screenovatetest.domain.entity.Payment
import com.alongo.screenovatetest.domain.entity.dto.PaymentDto
import com.alongo.screenovatetest.utils.mapper.DtoDisplayMapper

class PaymentDtoDisplayMapper : DtoDisplayMapper<PaymentDto, Payment>() {
    override fun toDisplay(obj: PaymentDto) =
        Payment(source = obj.data.source, destination = obj.data.destination, obj.data.amount)
}
