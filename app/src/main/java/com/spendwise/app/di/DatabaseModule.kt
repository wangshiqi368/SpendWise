package com.spendwise.app.di

import android.app.Application
import androidx.room.Room
import com.spendwise.app.data.local.AppDatabase
import com.spendwise.app.data.local.BudgetDao
import com.spendwise.app.data.local.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.spendwise.app.data.local.ExchangeRateDao
import com.spendwise.app.data.remote.ExchangeRateApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideExchangeRateApi(): ExchangeRateApi {
        return Retrofit.Builder()
            .baseUrl(ExchangeRateApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .addMigrations(AppDatabase.MIGRATION_2_3, AppDatabase.MIGRATION_3_4, AppDatabase.MIGRATION_4_5)
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

    @Provides
    @Singleton
    fun provideExchangeRateDao(db: AppDatabase): ExchangeRateDao {
        return db.exchangeRateDao
    }
}
