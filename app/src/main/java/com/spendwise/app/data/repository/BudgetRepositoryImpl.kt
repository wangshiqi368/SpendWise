package com.spendwise.app.data.repository

import com.spendwise.app.data.local.BudgetDao
import com.spendwise.app.data.local.BudgetEntity
import com.spendwise.app.domain.model.Budget
import com.spendwise.app.domain.model.Currency
import com.spendwise.app.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BudgetRepositoryImpl(
    private val dao: BudgetDao
) : BudgetRepository {

    override fun getBudgetForMonth(month: String): Flow<Budget?> {
        return dao.getBudgetForMonth(month).map { entity ->
            entity?.let { Budget(it.monthlyLimit, it.month, Currency.fromCode(it.currency)) }
        }
    }

    override suspend fun setBudget(budget: Budget) {
        dao.insertBudget(BudgetEntity(monthlyLimit = budget.monthlyLimit, month = budget.month, currency = budget.currency.code))
    }
}
