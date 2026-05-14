package com.spendwise.app.domain.model

enum class Currency(val code: String, val symbol: String) {
    CNY("CNY", "¥"),
    USD("USD", "$"),
    EUR("EUR", "€"),
    JPY("JPY", "¥"),
    HKD("HKD", "HK$"),
    GBP("GBP", "£");

    companion object {
        fun fromCode(code: String): Currency {
            return values().find { it.code == code } ?: CNY
        }
    }
}
