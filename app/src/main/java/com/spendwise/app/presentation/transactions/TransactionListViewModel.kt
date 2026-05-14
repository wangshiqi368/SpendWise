package com.spendwise.app.presentation.transactions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendwise.app.data.util.CsvExporter
import com.spendwise.app.domain.model.Budget
import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.use_case.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TransactionListState())
    val state: State<TransactionListState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getTransactionsJob: Job? = null
    private var getBudgetJob: Job? = null

    init {
        getTransactions()
        getBudget()
    }

    fun onEvent(event: TransactionListEvent) {
        when (event) {
            is TransactionListEvent.DeleteTransaction -> {
                viewModelScope.launch {
                    transactionUseCases.deleteTransaction(event.transaction)
                }
            }
            is TransactionListEvent.OnSearchQueryChange -> {
                _state.value = state.value.copy(searchQuery = event.query)
                getTransactions() // Refresh with filter
            }
            is TransactionListEvent.UpdateBudget -> {
                viewModelScope.launch {
                    transactionUseCases.setBudget(
                        Budget(
                            monthlyLimit = event.amount,
                            month = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"))
                        )
                    )
                }
            }
            is TransactionListEvent.ExportToCsv -> {
                viewModelScope.launch {
                    val csvData = CsvExporter.transactionsToCsv(state.value.transactions)
                    _eventFlow.emit(UiEvent.ExportCsv(csvData))
                }
            }
        }
    }

    sealed class UiEvent {
        data class ExportCsv(val csvData: String) : UiEvent()
    }

    private fun getBudget() {
        getBudgetJob?.cancel()
        val currentMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"))
        getBudgetJob = transactionUseCases.getBudget(currentMonth)
            .onEach { budget ->
                _state.value = state.value.copy(
                    monthlyBudget = budget?.monthlyLimit ?: 0.0
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getTransactions() {
        getTransactionsJob?.cancel()
        getTransactionsJob = transactionUseCases.getTransactions()
            .onEach { transactions ->
                val filteredTransactions = if (state.value.searchQuery.isBlank()) {
                    transactions
                } else {
                    transactions.filter { 
                        it.title.contains(state.value.searchQuery, ignoreCase = true) ||
                        it.category.contains(state.value.searchQuery, ignoreCase = true)
                    }
                }
                
                val total = filteredTransactions.sumOf { it.amount }
                
                _state.value = state.value.copy(
                    transactions = filteredTransactions,
                    totalSpending = total
                )
            }
            .launchIn(viewModelScope)
    }
}
