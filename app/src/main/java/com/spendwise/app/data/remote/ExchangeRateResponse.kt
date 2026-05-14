package com.spendwise.app.data.remote

data class ExchangeRateResponse(
    val base_code: String,
    val rates: Map<String, Double>
)
