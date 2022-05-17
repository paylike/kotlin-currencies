package io.paylike.kotlin_currencies

data class PaylikeCurrency(
    val code: String,
    val currency: String,
    val numeric: Int,
    val exponent: Int,
    val funding: Boolean,
    val deprecated: Boolean
) {
    fun fromJSON(json: Map<String, Any>)
    {
        //TODO parse from json
    }
}
