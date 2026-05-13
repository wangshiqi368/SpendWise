package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.repository.TransactionRepository

class AddTransaction(
    private val repository: TransactionRepository
) {
    @Throws(InvalidTransactionException::class)
    suspend operator fun invoke(transaction: Transaction) {
        if (transaction.title.isBlank()) {
            throw InvalidTransactionException("标题不能为空")
        }
        if (transaction.amount <= 0) {
            throw InvalidTransactionException("金额必须大于0")
        }
        repository.insertTransaction(transaction)
    }
}

class InvalidTransactionException(message: String) : Exception(message)
