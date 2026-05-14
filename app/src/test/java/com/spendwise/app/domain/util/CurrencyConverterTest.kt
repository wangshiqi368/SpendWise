package com.spendwise.app.domain.util

import com.spendwise.app.domain.model.Currency
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyConverterTest {

    @Test
    fun `convertToCny with CNY returns same amount`() {
        val amount = 100.0
        val result = CurrencyConverter.convertToCny(amount, Currency.CNY)
        assertEquals(100.0, result, 0.001)
    }

    @Test
    fun `convertToCny with USD returns correct calculated amount`() {
        val amount = 10.0
        // USD rate is 7.24
        val result = CurrencyConverter.convertToCny(amount, Currency.USD)
        assertEquals(72.4, result, 0.001)
    }

    @Test
    fun `convertFromCny with USD returns correct calculated amount`() {
        val amountCny = 72.4
        // USD rate is 7.24
        val result = CurrencyConverter.convertFromCny(amountCny, Currency.USD)
        assertEquals(10.0, result, 0.001)
    }
}
