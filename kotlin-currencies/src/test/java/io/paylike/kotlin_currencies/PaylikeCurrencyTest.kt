package io.paylike.kotlin_currencies

import io.paylike.kotlin_currencies.PaylikeCurrencies
import io.paylike.kotlin_currencies.generated.CurrencyCode
import io.paylike.kotlin_currencies.generated.PaylikeCurrencyCollection
import org.junit.Test
import org.junit.Assert.*

class PaylikeCurrencyTest {
    @Test
    fun findByCodeTest() {
        val eurCurrency: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.EUR)
        assertEquals(eurCurrency.code, "EUR")
        assertEquals(eurCurrency.currency, "Euro")
        assertEquals(eurCurrency.numeric, 978)
        assertEquals(eurCurrency.exponent, 2)
        assertEquals(eurCurrency.funding, true)
        assertEquals(eurCurrency.deprecated, false)
        val allCurrency: PaylikeCurrency = PaylikeCurrencies.byCode(CurrencyCode.ALL)
        assertEquals(allCurrency.code, "ALL")
        assertEquals(allCurrency.currency, "Albanian lek")
        assertEquals(allCurrency.numeric, 8)
        assertEquals(allCurrency.exponent, 2)
        assertEquals(allCurrency.funding, false)
        assertEquals(allCurrency.deprecated, false)
    }
    @Test
    fun majorMinorConversionTest() {
        assertEquals(1, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 100))
        assertEquals(100, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 1))

        assertEquals(1.01, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 101))
        assertEquals(101, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 1.01))

        assertEquals(0.01, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 1))
        assertEquals(1, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 0.01))

        assertEquals(100, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 10000))
        assertEquals(10000, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 100))

        assertEquals(1, PaylikeCurrencies.toMajor(CurrencyCode.JPY, 1))
        assertEquals(1, PaylikeCurrencies.toMinor(CurrencyCode.JPY, 1))
        assertEquals(0, PaylikeCurrencies.toMajor(CurrencyCode.JPY, 0))
        assertEquals(0, PaylikeCurrencies.toMinor(CurrencyCode.JPY, 0))

        assertEquals(1990, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 19.9))
        assertEquals(1999, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 19.99))
        assertEquals(1901, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 19.01))

        assertEquals(19.9, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 1990))
        assertEquals(19.99, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 1999))
        assertEquals(19.01, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 1901))

        assertEquals(99999, PaylikeCurrencies.toMinor(CurrencyCode.EUR, 999.99))
        assertEquals(999.99, PaylikeCurrencies.toMajor(CurrencyCode.EUR, 99999))
    }
    @Test
    fun getCurrencyValidTest() {
        val eurCurrency: CurrencyCode = PaylikeCurrencies.getCurrencyCode("EUR")
        assertEquals(CurrencyCode.EUR, eurCurrency)
        val allCurrency: CurrencyCode = PaylikeCurrencies.getCurrencyCode("ALL")
        assertEquals(CurrencyCode.ALL, allCurrency)
    }
    @Test
    fun byNumericValidTest() {
        val eurCurrency: PaylikeCurrency = PaylikeCurrencies.byNumeric(978)
        assertEquals(eurCurrency.code, "EUR")
        assertEquals(eurCurrency.currency, "Euro")
        assertEquals(eurCurrency.numeric, 978)
        assertEquals(eurCurrency.exponent, 2)
        assertEquals(eurCurrency.funding, true)
        assertEquals(eurCurrency.deprecated, false)
        val allCurrency: PaylikeCurrency = PaylikeCurrencies.byNumeric(8)
        assertEquals(allCurrency.code, "ALL")
        assertEquals(allCurrency.currency, "Albanian lek")
        assertEquals(allCurrency.numeric, 8)
        assertEquals(allCurrency.exponent, 2)
        assertEquals(allCurrency.funding, false)
        assertEquals(allCurrency.deprecated, false)
    }
    @Test
    fun exceptionTest() {
        try {
            PaylikeCurrencies.byNumeric(2355)
            fail("should not be able to reach this")
        } catch (e: MissingCurrencyException) {
            assertEquals(e is MissingCurrencyException, true)
            assertEquals(e.message, "Currency is missing")
            assertEquals(e.numeric, 2355)
            assertEquals(e.code, null)
            assertEquals(e.requestCode, null)
        }
        try {
            PaylikeCurrencies.getCurrencyCode("EURO")
            fail("should not be able to reach this")
        } catch (e: MissingCurrencyException) {
            assertEquals(e is MissingCurrencyException, true)
            assertEquals(e.message, "Currency is missing")
            assertEquals(e.numeric, null)
            assertEquals(e.code, null)
            assertEquals(e.requestCode, "EURO")
        }
    }
    @Test
    fun getListTest()
    {
        val paylikeCurrenciesList: List<PaylikeCurrency> = PaylikeCurrencyCollection.currencies.values.toList()
        assertEquals(paylikeCurrenciesList, PaylikeCurrencies.list())
    }

}
