package com.spendwise.app.domain.util

import com.spendwise.app.domain.model.Currency

object CurrencyConverter {
    fun convertToCny(amount: Double, from: Currency, rates: Map<String, Double>): Double {
        val rate = rates[from.code] ?: 1.0
        return amount * rate
    }

    fun convertFromCny(amountCny: Double, to: Currency, rates: Map<String, Double>): Double {
        val rate = rates[to.code] ?: 1.0
        return if (rate != 0.0) amountCny / rate else amountCny
    }
}
