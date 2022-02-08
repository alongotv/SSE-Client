package com.alongo.screenovatetest.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
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

        viewModel.loadPayments()
    }
}

@Composable
fun MainScreenView(viewModel: MainViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(viewModel.payments.size) {
            val item = viewModel.payments[it]
            BasicCard(source = item.source, destination = item.destination, amount = item.amount)
        }
    }
}

@Composable
fun BasicCard(source: String, destination: String, amount: Double) {
    val backgroundColor = if (amount >= 20) {
        Color.Green
    } else {
        Color.Red
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Text("Source: $source")
        Text("Destination: $destination")
        Text("Amount: $amount")
    }
}
