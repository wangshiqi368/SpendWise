package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTransactions(
    private val repository: TransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getTransactions()
    }
}
