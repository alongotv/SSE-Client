package com.alongo.screenovatetest.presentation.main

import androidx.lifecycle.ViewModel
import com.alongo.screenovatetest.data.networking.payment.PaymentApi
import com.alongo.screenovatetest.domain.entity.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val paymentApi: PaymentApi): ViewModel() {

    var payments: List<Payment> = emptyList()

    fun loadPayments() {
        paymentApi
        payments = listOf(Payment("", "", 10.0), Payment("", "", 20.0), Payment("", "", 30.0))
    }
}
