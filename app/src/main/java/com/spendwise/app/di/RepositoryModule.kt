package com.spendwise.app.di

import com.spendwise.app.data.local.TransactionDao
import com.spendwise.app.data.repository.TransactionRepositoryImpl
import com.spendwise.app.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTransactionRepository(
        dao: TransactionDao
    ): TransactionRepository {
        return TransactionRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideBudgetRepository(
        dao: BudgetDao
    ): BudgetRepository {
        return BudgetRepositoryImpl(dao)
    }
}
