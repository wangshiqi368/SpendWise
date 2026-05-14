package com.spendwise.app.presentation.transactions

import com.spendwise.app.domain.model.Transaction

sealed class TransactionListEvent {
    data class DeleteTransaction(val transaction: Transaction): TransactionListEvent()
    data class OnSearchQueryChange(val query: String): TransactionListEvent()
    data class UpdateBudget(val amount: Double): TransactionListEvent()
    data class OnMonthChange(val month: String): TransactionListEvent()
    object ExportToCsv: TransactionListEvent()
}
