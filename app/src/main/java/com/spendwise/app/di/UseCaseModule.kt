package com.spendwise.app.di

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
    fun provideTransactionUseCases(repository: TransactionRepository): TransactionUseCases {
        return TransactionUseCases(
            getTransactions = GetTransactions(repository),
            deleteTransaction = DeleteTransaction(repository),
            addTransaction = AddTransaction(repository),
            getTransaction = GetTransaction(repository)
        )
    }
}
