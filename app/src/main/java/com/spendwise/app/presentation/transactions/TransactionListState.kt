package com.spendwise.app.presentation.transactions

import com.spendwise.app.domain.model.Transaction

data class TransactionListState(
    val transactions: List<Transaction> = emptyList(),
    val searchQuery: String = "",
    val totalSpending: Double = 0.0,
    val monthlyBudget: Double = 0.0
)
