package com.spendwise.app.di

import com.spendwise.app.domain.repository.BudgetRepository
import com.spendwise.app.domain.repository.ExchangeRateRepository
import com.spendwise.app.domain.repository.TransactionRepository
import com.spendwise.app.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideTransactionUseCases(
        repository: TransactionRepository,
        budgetRepository: BudgetRepository,
        exchangeRateRepository: ExchangeRateRepository
    ): TransactionUseCases {
        return TransactionUseCases(
            getTransactions = GetTransactions(repository),
            deleteTransaction = DeleteTransaction(repository),
            addTransaction = AddTransaction(repository),
            getTransaction = GetTransaction(repository),
            getBudget = GetBudget(budgetRepository),
            setBudget = SetBudget(budgetRepository),
            getCategoryStats = GetCategoryStats(),
            syncExchangeRates = SyncExchangeRates(exchangeRateRepository),
            getExchangeRates = GetExchangeRates(exchangeRateRepository)
        )
    }
}
