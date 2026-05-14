package com.spendwise.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [TransactionEntity::class, BudgetEntity::class, ExchangeRateEntity::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract val transactionDao: TransactionDao
    abstract val budgetDao: BudgetDao
    abstract val exchangeRateDao: ExchangeRateDao

    companion object {
        const val DATABASE_NAME = "spendwise_db"

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE transactions ADD COLUMN imagePath TEXT")
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE transactions ADD COLUMN currency TEXT NOT NULL DEFAULT 'CNY'")
                db.execSQL("ALTER TABLE budget ADD COLUMN currency TEXT NOT NULL DEFAULT 'CNY'")
            }
        }

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `exchange_rates` (`currencyCode` TEXT NOT NULL, `rateToCny` REAL NOT NULL, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`currencyCode`))")
            }
        }
    }
}
