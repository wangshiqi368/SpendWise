package com.spendwise.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [TransactionEntity::class, BudgetEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract val transactionDao: TransactionDao
    abstract val budgetDao: BudgetDao

    companion object {
        const val DATABASE_NAME = "spendwise_db"

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE transactions ADD COLUMN imagePath TEXT")
            }
        }
    }
}
