package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.repository.ExchangeRateRepository

class GetExchangeRates(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(): Map<String, Double> {
        return repository.getRates()
    }
}
