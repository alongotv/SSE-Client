package com.alongo.screenovatetest.presentation.main

import androidx.compose.runtime.mutableStateListOf
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
class MainViewModel @Inject constructor(private val paymentApi: PaymentApi) : ViewModel() {

    val payments = mutableStateListOf<Payment>()

    fun loadPayments() {
        subscribeToPayments()
    }

    private fun subscribeToPayments() {
        viewModelScope.launch {
            while (isActive) {
                paymentApi.getPayments().collect {
                    payments.toMutableList().add(it)
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
