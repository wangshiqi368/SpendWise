package com.spendwise.app.domain.use_case

import androidx.compose.ui.graphics.Color
import com.spendwise.app.domain.model.Category
import com.spendwise.app.domain.model.CategoryStat
import com.spendwise.app.domain.model.Transaction

class GetCategoryStats {
    operator fun invoke(transactions: List<Transaction>): List<CategoryStat> {
        if (transactions.isEmpty()) return emptyList()

        val totalAll = transactions.sumOf { it.amount }
        if (totalAll <= 0) return emptyList()

        val grouped = transactions.groupBy { it.category }
        
        val colors = listOf(
            Color(0xFF6200EE), Color(0xFF03DAC5), Color(0xFFFFB74D),
            Color(0xFF4FC3F7), Color(0xFF9575CD), Color(0xFFF06292),
            Color(0xFFAED581), Color(0xFFFF8A65)
        )

        return grouped.entries.mapIndexed { index, entry ->
            val total = entry.value.sumOf { it.amount }
            val category = Category.defaultCategories.find { it.name == entry.key }
                ?: Category(entry.key, "📦")
            
            CategoryStat(
                categoryName = entry.key,
                categoryIcon = category.icon,
                totalAmount = total,
                percentage = (total / totalAll).toFloat(),
                color = colors[index % colors.size]
            )
        }.sortedByDescending { it.totalAmount }
    }
}
