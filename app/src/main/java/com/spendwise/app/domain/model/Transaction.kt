package com.spendwise.app.domain.model

import java.time.LocalDateTime

data class Transaction(
    val id: Long? = null,
    val title: String,
    val amount: Double,
    val category: String,
    val date: LocalDateTime,
    val note: String? = null,
    val imagePath: String? = null,
    val currency: Currency = Currency.CNY
)
