package com.spendwise.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val amount: Double,
    val category: String,
    val date: String, // Stored as ISO string
    val note: String?
)
