package com.spendwise.app.presentation.transactions

import com.spendwise.app.domain.model.Transaction

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TransactionListState(
    val transactions: List<Transaction> = emptyList(),
    val searchQuery: String = "",
    val totalSpending: Double = 0.0,
    val monthlyBudget: Double = 0.0,
    val selectedMonth: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"))
)
