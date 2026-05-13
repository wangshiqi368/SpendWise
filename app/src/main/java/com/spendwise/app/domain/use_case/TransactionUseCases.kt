package com.spendwise.app.domain.use_case

data class TransactionUseCases(
    val getTransactions: GetTransactions,
    val deleteTransaction: DeleteTransaction,
    val addTransaction: AddTransaction,
    val getTransaction: GetTransaction,
    val getBudget: GetBudget,
    val setBudget: SetBudget
)
