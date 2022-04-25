package com.alongo.ssetest.domain.entity.dto

import com.google.gson.annotations.SerializedName

data class PaymentDataDto(
    @SerializedName("from") val source: String = "default1",
    @SerializedName("to") val destination: String = "default2",
    @SerializedName("amount") val amount: Double = 1.0
)
