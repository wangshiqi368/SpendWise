package com.spendwise.app.presentation.statistics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendwise.app.domain.model.CategoryStat
import com.spendwise.app.domain.model.CurrencyStat
import com.spendwise.app.domain.use_case.TransactionUseCases
import com.spendwise.app.domain.util.CurrencyConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class StatType {
    CATEGORY, CURRENCY
}

data class StatisticsState(
    val categoryStats: List<CategoryStat> = emptyList(),
    val currencyStats: List<CurrencyStat> = emptyList(),
    val totalSpending: Double = 0.0,
    val selectedTab: StatType = StatType.CATEGORY
)

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(StatisticsState())
    val state: State<StatisticsState> = _state

    init {
        getStats()
    }

    fun onEvent(event: StatisticsEvent) {
        when(event) {
            is StatisticsEvent.OnTabSelected -> {
                _state.value = _state.value.copy(selectedTab = event.tab)
            }
        }
    }

    private fun getStats() {
        transactionUseCases.getTransactions()
            .onEach { transactions ->
                val rates = transactionUseCases.getExchangeRates()
                
                // Category Stats (converted to CNY)
                val cnyTransactions = transactions.map {
                    it.copy(amount = CurrencyConverter.convertToCny(it.amount, it.currency, rates))
                }
                val categoryStats = transactionUseCases.getCategoryStats(cnyTransactions)
                
                // Currency Stats
                val currencyStats = transactionUseCases.getCurrencyStats(transactions, rates)

                _state.value = _state.value.copy(
                    categoryStats = categoryStats,
                    currencyStats = currencyStats,
                    totalSpending = categoryStats.sumOf { it.totalAmount }
                )
            }
            .launchIn(viewModelScope)
    }
}

sealed class StatisticsEvent {
    data class OnTabSelected(val tab: StatType) : StatisticsEvent()
}
