package com.spendwise.app.domain.repository

import com.spendwise.app.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getBudgetForMonth(month: String): Flow<Budget?>
    suspend fun setBudget(budget: Budget)
}
