package io.paylike.kotlin_currencies

import io.paylike.kotlin_currencies.generated.CurrencyCode
import io.paylike.kotlin_currencies.generated.PaylikeCurrencyCollection

object PaylikeCurrencies {
    fun getCurrencyCode(code: String): CurrencyCode // TODO
    {
        return CurrencyCode.AED // TODO
    }
    fun byCode(code: CurrencyCode): PaylikeCurrency? // TODO
    {
        return PaylikeCurrencyCollection.currencies[code]// TODO
    }
    fun list(): List<PaylikeCurrency> // TODO
    {
        var temp: List<PaylikeCurrency> = list()
        PaylikeCurrencyCollection.currencies.forEach { temp.plus(it.value) }
        return temp  // GIGANTIC TODO
    }
    fun byNumeric(code: String): Unit // TODO
    {
        // TODO
    }
}