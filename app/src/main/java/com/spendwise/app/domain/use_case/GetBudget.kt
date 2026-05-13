package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Budget
import com.spendwise.app.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow

class GetBudget(
    private val repository: BudgetRepository
) {
    operator fun invoke(month: String): Flow<Budget?> {
        return repository.getBudgetForMonth(month)
    }
}
