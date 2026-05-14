package com.spendwise.app.domain.repository

interface ExchangeRateRepository {
    suspend fun syncRates(): Result<Unit>
    suspend fun getRates(): Map<String, Double>
}
