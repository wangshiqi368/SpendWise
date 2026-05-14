package com.spendwise.app.data.util

import com.spendwise.app.domain.model.Transaction
import java.time.format.DateTimeFormatter

object CsvExporter {
    fun transactionsToCsv(transactions: List<Transaction>): String {
        val header = "ID,Title,Amount,Category,Date,Note\n"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        
        val rows = transactions.joinToString("\n") { transaction ->
            listOf(
                transaction.id ?: "",
                transaction.title.escapeCsv(),
                transaction.amount.toString(),
                transaction.category.escapeCsv(),
                transaction.date.format(formatter),
                (transaction.note ?: "").escapeCsv()
            ).joinToString(",")
        }
        
        return header + rows
    }

    private fun String.escapeCsv(): String {
        return if (contains(",") || contains("\"") || contains("\n")) {
            "\"" + replace("\"", "\"\"") + "\""
        } else {
            this
        }
    }
}
