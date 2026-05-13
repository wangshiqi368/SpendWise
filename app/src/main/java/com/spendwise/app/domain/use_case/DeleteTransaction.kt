package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.repository.TransactionRepository

class DeleteTransaction(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.deleteTransaction(transaction)
    }
}
