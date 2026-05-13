package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.repository.TransactionRepository

class GetTransaction(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(id: Long): Transaction? {
        return repository.getTransactionById(id)
    }
}
