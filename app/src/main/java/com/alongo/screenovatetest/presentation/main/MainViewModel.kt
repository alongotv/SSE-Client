package com.alongo.screenovatetest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.screenovatetest.data.networking.payment.PaymentApi
import com.alongo.screenovatetest.domain.entity.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(private val paymentApi: PaymentApi): ViewModel() {

    var payments: MutableList<Payment> = mutableListOf()

    fun loadPayments() {
        subscribeToPayments()
        payments = mutableListOf(Payment("", "", 10.0), Payment("", "", 20.0), Payment("", "", 30.0))
    }

    private fun subscribeToPayments() {
        viewModelScope.launch {
            while (isActive) {
                paymentApi.getPayments().collect {
                    payments.add(it)
                    Timber.i(it.toString())
                }
                delay(paymentLoadingDelayMillis)
            }
        }
    }
    companion object {
        const val paymentLoadingDelayMillis = 5000L
    }
}
