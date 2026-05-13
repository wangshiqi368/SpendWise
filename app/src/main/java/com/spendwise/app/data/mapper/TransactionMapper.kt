package com.spendwise.app.data.mapper

import com.spendwise.app.data.local.TransactionEntity
import com.spendwise.app.domain.model.Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

fun TransactionEntity.toTransaction(): Transaction {
    return Transaction(
        id = id,
        title = title,
        amount = amount,
        category = category,
        date = LocalDateTime.parse(date, formatter),
        note = note
    )
}

fun Transaction.toTransactionEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        title = title,
        amount = amount,
        category = category,
        date = date.format(formatter),
        note = note
    )
}
