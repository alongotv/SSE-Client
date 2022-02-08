package com.alongo.screenovatetest.domain.entity.dto

import com.google.gson.annotations.SerializedName

data class PaymentDataDto(
    @SerializedName("from") val source: String,
    @SerializedName("to") val destination: String,
    @SerializedName("amount") val amount: Double
)
