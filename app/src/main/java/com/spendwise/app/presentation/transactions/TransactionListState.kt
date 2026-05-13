package com.spendwise.app.presentation.transactions

import com.spendwise.app.domain.model.Transaction

data class TransactionListState(
    val transactions: List<Transaction> = emptyList()
)
