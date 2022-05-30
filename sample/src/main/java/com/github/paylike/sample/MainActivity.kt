package com.github.paylike.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.paylike.kotlin_currencies.PaylikeCurrencies
import com.github.paylike.kotlin_currencies.PaylikeCurrency
import com.github.paylike.kotlin_currencies.generated.CurrencyCode
import com.github.paylike.kotlin_currencies.generated.PaylikeCurrencyCollection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lookup CurrencyCode
        val eurCode: CurrencyCode = PaylikeCurrencies.getCurrencyCode("EUR")
        println(eurCode.name) // "EUR"
        // lookup by code
        val eur: PaylikeCurrency = PaylikeCurrencies.byCode(eurCode)
        // { code: "EUR", currency: "Euro", numeric: 978 }
        println(eur.code) // "EUR"
        println(eur.currency) // "Euro"
        println(eur.numeric) // 978
        // get list of currencies
        val paylikeCurrenciesList: List<PaylikeCurrency> = PaylikeCurrencyCollection.currencies.values.toList()
        // convert between minor and major respecting the exponent
        var major: Number = 100.00
        var minor = PaylikeCurrencies.toMinor(CurrencyCode.DKK, major)
        println(minor) // 10000
        major = PaylikeCurrencies.toMajor(CurrencyCode.DKK, minor)
        println(major) // 100
    }
}
