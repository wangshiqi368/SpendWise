package com.spendwise.app.presentation.settings

sealed class SettingsEvent {
    object ExportToCsv : SettingsEvent()
    object RefreshExchangeRates : SettingsEvent()
}
