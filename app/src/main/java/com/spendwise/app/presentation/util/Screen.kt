package com.spendwise.app.presentation.util

sealed class Screen(val route: String) {
    object TransactionListScreen: Screen("transaction_list_screen")
    object AddEditTransactionScreen: Screen("add_edit_transaction_screen")
    object StatisticsScreen: Screen("statistics_screen")
    object SettingsScreen: Screen("settings_screen")
}
