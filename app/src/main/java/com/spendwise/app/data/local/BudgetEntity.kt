package com.spendwise.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget")
data class BudgetEntity(
    @PrimaryKey
    val id: Int = 0, // We only need one budget for now
    val monthlyLimit: Double,
    val month: String // Format: YYYY-MM
)
