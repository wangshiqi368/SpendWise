package com.spendwise.app.presentation.statistics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendwise.app.domain.model.CategoryStat
import com.spendwise.app.domain.use_case.TransactionUseCases
import com.spendwise.app.domain.util.CurrencyConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(StatisticsState())
    val state: State<StatisticsState> = _state

    init {
        getStats()
    }

    private fun getStats() {
        transactionUseCases.getTransactions()
            .onEach { transactions ->
                val rates = transactionUseCases.getExchangeRates()
                // Convert all transactions to CNY for statistics
                val cnyTransactions = transactions.map { 
                    it.copy(amount = CurrencyConverter.convertToCny(it.amount, it.currency, rates))
                }
                val stats = transactionUseCases.getCategoryStats(cnyTransactions)
                _state.value = state.value.copy(
                    categoryStats = stats,
                    totalSpending = stats.sumOf { it.totalAmount }
                )
            }
            .launchIn(viewModelScope)
    }
}

data class StatisticsState(
    val categoryStats: List<CategoryStat> = emptyList(),
    val totalSpending: Double = 0.0
)
