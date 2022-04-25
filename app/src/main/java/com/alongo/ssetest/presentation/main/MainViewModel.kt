package com.alongo.ssetest.presentation.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.ssetest.data.networking.payment.PaymentApi
import com.alongo.ssetest.domain.entity.Payment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(private val paymentApi: PaymentApi) : ViewModel() {

    val payments = mutableStateListOf<Payment>()

    private var paymentJob: Job? = null

    fun loadPayments() {
        viewModelScope.launch {
            while (isActive) {
                paymentJob?.cancel()
                payments.clear()
                subscribeToPayments()
                delay(paymentLoadingDelayMillis)
            }
        }
    }

    private fun subscribeToPayments() {
        paymentJob = viewModelScope.launch {
            paymentApi.getPayments().collect {
                withContext(Dispatchers.Main) {
                    payments.add(it)
                }
                Timber.i(it.toString())
            }
        }
    }

    companion object {
        const val paymentLoadingDelayMillis = 10000L
    }
}
