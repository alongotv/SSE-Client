package com.alongo.screenovatetest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.screenovatetest.data.networking.payment.PaymentApi
import com.alongo.screenovatetest.domain.entity.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val paymentApi: PaymentApi): ViewModel() {

    var payments: List<Payment> = emptyList()

    fun loadPayments() {
        subscribeToPayments()
        payments = listOf(Payment("", "", 10.0), Payment("", "", 20.0), Payment("", "", 30.0))
    }

    fun subscribeToPayments() {
        viewModelScope.launch {
            paymentApi.getPayments().collect {
                println(it)
            }
        }
    }
}
