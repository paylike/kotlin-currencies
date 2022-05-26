# Currencies

<a href="https://jitpack.io/#paylike/kotlin-currencies" target="_blank">
    <img src="https://jitpack.io/v/paylike/kotlin-currencies.svg" />
</a>

This library is a clone of the [JS version](https://github.com/paylike/currencies)

List of currencies supported by Paylike for transactions and accounts.

Read more:

- https://paylike.io/account/currency
- https://paylike.io/transactions/currencies

## Account currencies

These are the currencies you can use as a base for your account. Also known as
account, settlement and funding currency.

### Global

- EUR (Euro)
- USD (United States dollar)
- GBP (British pound sterling)

### Nordic

- DKK (Danish krone)
- NOK (Norwegian krone)
- SEK (Swedish krona)

### Other EU

- CHF (Swiss franc)
- HUF (Hungarian forint)
- PLN (Polish złoty)
- RON (Romanian leu)
- CZK (Czech koruna)
- BGN (Bulgarian lev)
- HRK (Croatian kuna)

## Usage

To include the lib, you may add the following groovy code to your gradle build files

```groovy
repositories {
    //...
    maven { url "https://jitpack.io" }
}
dependencies {
    //...
    implementation 'com.github.paylike:kotlin-currencies:v1.0.2'
}
```


Within the Paylike ecosystem you should use the `CurrencyCode` enum to refer to a currency.

```kotlin 
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
```
