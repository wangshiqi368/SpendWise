package com.spendwise.app.presentation.transactions

import com.spendwise.app.domain.model.Budget
import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.use_case.TransactionUseCases
import com.spendwise.app.util.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCases: TransactionUseCases
    private lateinit var viewModel: TransactionListViewModel

    @Before
    fun setUp() {
        useCases = mockk(relaxed = true)

        val currentMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"))
        val transactions = listOf(
            Transaction(title = "Food", amount = 100.0, category = "餐饮", date = LocalDateTime.now()),
            Transaction(title = "Taxi", amount = 50.0, category = "交通", date = LocalDateTime.now()),
            Transaction(title = "Old", amount = 20.0, category = "餐饮", date = LocalDateTime.now().minusMonths(1))
        )

        every { useCases.getTransactions() } returns flowOf(transactions)
        every { useCases.getBudget(any()) } returns flowOf(Budget(2000.0, currentMonth))

        viewModel = TransactionListViewModel(useCases)
    }

    @Test
    fun `initial state loads transactions for current month only`() {
        val state = viewModel.state.value

        assertEquals(2, state.transactions.size)
        assertEquals(150.0, state.totalSpending, 0.001)
        assertEquals(2000.0, state.monthlyBudget, 0.001)
    }

    @Test
    fun `search query filters transactions by title and category`() {
        viewModel.onEvent(TransactionListEvent.OnSearchQueryChange("Food"))
        
        val state1 = viewModel.state.value
        assertEquals(1, state1.transactions.size)
        assertEquals("Food", state1.transactions[0].title)

        viewModel.onEvent(TransactionListEvent.OnSearchQueryChange("交通"))
        
        val state2 = viewModel.state.value
        assertEquals(1, state2.transactions.size)
        assertEquals("Taxi", state2.transactions[0].title)
    }

    @Test
    fun `changing month loads data for that month`() {
        val oldMonth = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"))
        every { useCases.getBudget(oldMonth) } returns flowOf(Budget(1500.0, oldMonth))

        viewModel.onEvent(TransactionListEvent.OnMonthChange(oldMonth))

        val state = viewModel.state.value
        assertEquals(1, state.transactions.size)
        assertEquals("Old", state.transactions[0].title)
        assertEquals(1500.0, state.monthlyBudget, 0.001)
    }
}
