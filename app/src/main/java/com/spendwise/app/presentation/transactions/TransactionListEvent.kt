package com.spendwise.app.presentation.transactions

import com.spendwise.app.domain.model.Transaction

sealed class TransactionListEvent {
    data class DeleteTransaction(val transaction: Transaction): TransactionListEvent()
}
