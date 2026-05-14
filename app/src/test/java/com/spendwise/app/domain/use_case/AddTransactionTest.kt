package com.spendwise.app.domain.use_case

import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.repository.TransactionRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class AddTransactionTest {

    private lateinit var addTransaction: AddTransaction
    private lateinit var repository: TransactionRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        addTransaction = AddTransaction(repository)
    }

    @Test(expected = InvalidTransactionException::class)
    fun `add transaction with empty title, throws exception`() = runBlocking {
        val transaction = Transaction(
            title = "",
            amount = 100.0,
            category = "餐饮",
            date = LocalDateTime.now()
        )
        addTransaction(transaction)
    }

    @Test(expected = InvalidTransactionException::class)
    fun `add transaction with zero or negative amount, throws exception`() = runBlocking {
        val transaction = Transaction(
            title = "Test",
            amount = 0.0,
            category = "餐饮",
            date = LocalDateTime.now()
        )
        addTransaction(transaction)
    }

    @Test
    fun `add valid transaction, calls repository insert`() = runBlocking {
        val transaction = Transaction(
            title = "Test",
            amount = 100.0,
            category = "餐饮",
            date = LocalDateTime.now()
        )
        addTransaction(transaction)

        coVerify { repository.insertTransaction(transaction) }
    }
}
