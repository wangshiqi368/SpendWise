package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.repository.ExchangeRateRepository

class SyncExchangeRates(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.syncRates()
    }
}
