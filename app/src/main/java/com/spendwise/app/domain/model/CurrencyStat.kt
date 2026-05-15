package com.spendwise.app.domain.model

import androidx.compose.ui.graphics.Color

data class CurrencyStat(
    val currency: Currency,
    val totalAmountInCny: Double,
    val percentage: Float,
    val color: Color
)
