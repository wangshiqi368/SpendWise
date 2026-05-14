package com.spendwise.app.domain.model

import androidx.compose.ui.graphics.Color

data class CategoryStat(
    val categoryName: String,
    val categoryIcon: String,
    val totalAmount: Double,
    val percentage: Float,
    val color: Color
)
