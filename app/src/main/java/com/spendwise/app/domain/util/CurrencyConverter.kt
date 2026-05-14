package com.spendwise.app.domain.util

import com.spendwise.app.domain.model.Currency

object CurrencyConverter {
    // Fixed exchange rates relative to CNY (Mock for now)
    private val ratesToCny = mapOf(
        Currency.CNY to 1.0,
        Currency.USD to 7.24,
        Currency.EUR to 7.85,
        Currency.JPY to 0.046,
        Currency.HKD to 0.93,
        Currency.GBP to 9.15
    )

    fun convertToCny(amount: Double, from: Currency): Double {
        val rate = ratesToCny[from] ?: 1.0
        return amount * rate
    }

    fun convertFromCny(amountCny: Double, to: Currency): Double {
        val rate = ratesToCny[to] ?: 1.0
        return amountCny / rate
    }
}
