package com.github.paylike.kotlin_currencies

import kotlinx.serialization.Serializable

@Serializable
data class PaylikeCurrency(
    val code: String,
    val currency: String,
    val numeric: Int,
    val exponent: Int,
    val funding: Boolean,
    val deprecated: Boolean
)
