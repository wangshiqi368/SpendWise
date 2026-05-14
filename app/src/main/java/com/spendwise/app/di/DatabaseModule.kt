package com.spendwise.app.di

import android.app.Application
import androidx.room.Room
import com.spendwise.app.data.local.AppDatabase
import com.spendwise.app.data.local.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .addMigrations(AppDatabase.MIGRATION_2_3)
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(db: AppDatabase): TransactionDao {
        return db.transactionDao
    }

    @Provides
    @Singleton
    fun provideBudgetDao(db: AppDatabase): BudgetDao {
        return db.budgetDao
    }
}
