package com.spendwise.app.data.remote

import retrofit2.http.GET

interface ExchangeRateApi {
    @GET("v4/latest/CNY")
    suspend fun getLatestRates(): ExchangeRateResponse

    companion object {
        const val BASE_URL = "https://api.exchangerate-api.com/"
    }
}
