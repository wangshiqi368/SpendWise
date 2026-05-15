package com.spendwise.app.domain.use_case

import androidx.compose.ui.graphics.Color
import com.spendwise.app.domain.model.CurrencyStat
import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.util.CurrencyConverter

class GetCurrencyStats {
    suspend operator fun invoke(transactions: List<Transaction>, rates: Map<String, Double>): List<CurrencyStat> {
        if (transactions.isEmpty()) return emptyList()

        val transactionsInCny = transactions.map {
            it.copy(amount = CurrencyConverter.convertToCny(it.amount, it.currency, rates))
        }
        val totalAll = transactionsInCny.sumOf { it.amount }
        if (totalAll <= 0) return emptyList()

        val groupedByCurrency = transactions.groupBy { it.currency }

        val colors = listOf(
            Color(0xFF4CAF50), Color(0xFF2196F3), Color(0xFFFFC107),
            Color(0xFFE91E63), Color(0xFF9C27B0), Color(0xFF795548)
        )

        return groupedByCurrency.entries.mapIndexed { index, entry ->
            val currency = entry.key
            val totalInCny = entry.value.sumOf { CurrencyConverter.convertToCny(it.amount, it.currency, rates) }
            
            CurrencyStat(
                currency = currency,
                totalAmountInCny = totalInCny,
                percentage = (totalInCny / totalAll).toFloat(),
                color = colors[index % colors.size]
            )
        }.sortedByDescending { it.totalAmountInCny }
    }
}
