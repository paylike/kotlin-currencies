package com.github.paylike.kotlin_currencies

import com.github.paylike.kotlin_currencies.generated.CurrencyCode

class MissingCurrencyException() : Exception() {
    constructor(code: CurrencyCode) : this() {
        this.code = code
    }
    constructor(numeric: Int) : this() {
        this.numeric = numeric
    }
    constructor(requestCode: String) : this() {
        this.requestCode = requestCode
    }
    override val message: String = "Currency is missing"
    var code: CurrencyCode? = null
    var numeric: Int? = null
    var requestCode: String? = null
}
