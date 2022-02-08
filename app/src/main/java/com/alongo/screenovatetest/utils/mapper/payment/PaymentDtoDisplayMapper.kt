package com.alongo.screenovatetest.utils.mapper.payment

import com.alongo.screenovatetest.domain.entity.Payment
import com.alongo.screenovatetest.domain.entity.dto.PaymentDataDto
import com.alongo.screenovatetest.domain.entity.dto.PaymentDto
import com.alongo.screenovatetest.utils.mapper.DtoDisplayMapper

class PaymentDtoDisplayMapper : DtoDisplayMapper<PaymentDataDto, Payment>() {
    override fun toDisplay(obj: PaymentDataDto) =
        Payment(source = obj.source, destination = obj.destination, obj.amount)
}
