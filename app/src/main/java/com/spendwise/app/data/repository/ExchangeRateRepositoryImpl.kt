package com.spendwise.app.data.repository

import com.spendwise.app.data.local.ExchangeRateDao
import com.spendwise.app.data.local.ExchangeRateEntity
import com.spendwise.app.data.remote.ExchangeRateApi
import com.spendwise.app.domain.repository.ExchangeRateRepository

class ExchangeRateRepositoryImpl(
    private val api: ExchangeRateApi,
    private val dao: ExchangeRateDao
) : ExchangeRateRepository {

    override suspend fun syncRates(): Result<Unit> {
        return try {
            val response = api.getLatestRates()
            val entities = response.rates.map { (currencyCode, rate) ->
                ExchangeRateEntity(
                    currencyCode = currencyCode,
                    // The API returns rates relative to CNY (e.g., 1 CNY = X USD)
                    // We want to store how many CNY is 1 unit of the currency (e.g., 1 USD = 7.2 CNY)
                    // So we invert it.
                    rateToCny = if (rate != 0.0) 1.0 / rate else 1.0,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            dao.insertRates(entities)
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getRates(): Map<String, Double> {
        return dao.getAllRates().associate { it.currencyCode to it.rateToCny }
    }
}
