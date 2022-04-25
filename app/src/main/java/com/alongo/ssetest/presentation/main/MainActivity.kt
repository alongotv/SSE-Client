package com.alongo.ssetest.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alongo.ssetest.R
import com.alongo.ssetest.presentation.view.BasicCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ComposeView(this).apply {
            setContent {
                MainScreenView(viewModel)
            }
        })
    }
}

@Composable
fun MainScreenView(viewModel: MainViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(viewModel.payments.size) {
            val item = viewModel.payments[it]
            MainCard(source = item.source, destination = item.destination, amount = item.amount)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.loadPayments()
    }
}

@Composable
fun MainCard(source: String, destination: String, amount: Double) {
    val backgroundColor = if (amount >= 20) {
        Color.Green
    } else {
        Color.Red
    }

    BasicCard(
        header = stringResource(id = R.string.main_screen_source, source),
        body = stringResource(id = R.string.main_screen_destination, destination),
        footer = stringResource(id = R.string.main_screen_amount, amount),
        backgroundColor = backgroundColor
    )
}
