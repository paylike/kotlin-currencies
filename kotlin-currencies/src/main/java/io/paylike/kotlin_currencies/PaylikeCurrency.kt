package io.paylike.kotlin_currencies

data class PaylikeCurrency(
    val code: String,
    val currency: String,
    val numeric: Int,
    val exponent: Int,
    val funding: Boolean,
    val deprecated: Boolean
) {

    // TODO: is it even necessary?

    constructor(json: Map<String, Any>) :
            this(
                json.get("code") as String,
                json.get("currency") as String,
                json.get("numeric") as Int,
                json.get("exponent") as Int,
                json.get("code") != null,
                json.get("code") != null
    )
}