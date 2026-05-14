package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Currency
import com.spendwise.app.domain.model.Transaction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class GetCategoryStatsTest {

    private lateinit var getCategoryStats: GetCategoryStats

    @Before
    fun setUp() {
        getCategoryStats = GetCategoryStats()
    }

    @Test
    fun `empty transaction list returns empty stats list`() {
        val stats = getCategoryStats(emptyList())
        assertTrue(stats.isEmpty())
    }

    @Test
    fun `transactions with zero total returns empty stats list`() {
        val transactions = listOf(
            Transaction(
                title = "Test",
                amount = 0.0,
                category = "餐饮",
                date = LocalDateTime.now()
            )
        )
        val stats = getCategoryStats(transactions)
        assertTrue(stats.isEmpty())
    }

    @Test
    fun `valid transactions returns sorted stats with correct percentages`() {
        val transactions = listOf(
            Transaction(title = "T1", amount = 50.0, category = "餐饮", date = LocalDateTime.now()),
            Transaction(title = "T2", amount = 150.0, category = "餐饮", date = LocalDateTime.now()),
            Transaction(title = "T3", amount = 100.0, category = "交通", date = LocalDateTime.now()),
            Transaction(title = "T4", amount = 100.0, category = "娱乐", date = LocalDateTime.now())
        )

        val stats = getCategoryStats(transactions)

        assertEquals(3, stats.size)
        // Total amount is 400.0
        // 餐饮: 200.0 (50%)
        // 交通: 100.0 (25%)
        // 娱乐: 100.0 (25%)

        // Check sorting (descending by totalAmount)
        assertEquals("餐饮", stats[0].categoryName)
        assertEquals(200.0, stats[0].totalAmount, 0.001)
        assertEquals(0.5f, stats[0].percentage, 0.001f)

        // The order of next two with same amount depends on group iteration order, but both should have 100.0
        assertEquals(100.0, stats[1].totalAmount, 0.001)
        assertEquals(0.25f, stats[1].percentage, 0.001f)

        assertEquals(100.0, stats[2].totalAmount, 0.001)
        assertEquals(0.25f, stats[2].percentage, 0.001f)
    }
}
