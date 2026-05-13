package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Budget
import com.spendwise.app.domain.repository.BudgetRepository

class SetBudget(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(budget: Budget) {
        repository.setBudget(budget)
    }
}
