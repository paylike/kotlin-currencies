package io.paylike.kotlin_currencies

import android.icu.math.BigDecimal
import io.paylike.kotlin_currencies.generated.CurrencyCode
import io.paylike.kotlin_currencies.generated.PaylikeCurrencyCollection
import java.lang.StrictMath.pow

object PaylikeCurrencies {
    fun getCurrencyCode(code: String): CurrencyCode
    {
        val currencyCode = PaylikeCurrencyCollection.currencies.entries.find { it -> it.value.code == code }?.key
        if (currencyCode == null)
        {
            throw MissingCurrencyException(code)
        }
        return currencyCode
    }
    fun byNumeric(numeric: Int): PaylikeCurrency
    {
        var element: PaylikeCurrency? = PaylikeCurrencyCollection.currencies.entries.find { it -> it.value.numeric == numeric }?.value
        if (element == null)
        {
            throw MissingCurrencyException(numeric)
        }
        return element
    }
    fun byCode(code: CurrencyCode): PaylikeCurrency
    {
        val element: PaylikeCurrency? = PaylikeCurrencyCollection.currencies[code]
        if (element == null)
        {
            throw MissingCurrencyException(code)
        }
        return element
    }
    fun list(): List<PaylikeCurrency>
    {
        return PaylikeCurrencyCollection.currencies.values.toList()
    }
    fun toMinor(code: CurrencyCode, major: Number): Number {
        return (major.toDouble() * pow(10.0, byCode(code).exponent.toDouble()))
            .toBigDecimal()
            .setScale(2, BigDecimal.ROUND_HALF_UP)
            .toInt()
    }
    fun toMajor(code: CurrencyCode, minor: Number): Number
    {
        val temp = (minor.toDouble() / pow(10.0, byCode(code).exponent.toDouble()))
            .toBigDecimal()
            .setScale(2, BigDecimal.ROUND_HALF_UP)
        if (isRound(temp))
            return temp.toInt()
        return temp.toDouble()
    }
    private fun isRound(number: Number) = (number.toDouble() * 100.0) % 100.0 == 0.0 // checks whether its round
}
