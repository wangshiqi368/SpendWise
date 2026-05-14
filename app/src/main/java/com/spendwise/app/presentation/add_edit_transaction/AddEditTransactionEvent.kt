package com.spendwise.app.presentation.add_edit_transaction

sealed class AddEditTransactionEvent {
    data class EnteredTitle(val value: String): AddEditTransactionEvent()
    data class EnteredAmount(val value: String): AddEditTransactionEvent()
    data class EnteredCategory(val value: String): AddEditTransactionEvent()
    data class EnteredImage(val uri: android.net.Uri?): AddEditTransactionEvent()
    data class ChangedCurrency(val currency: com.spendwise.app.domain.model.Currency): AddEditTransactionEvent()
    object SaveTransaction: AddEditTransactionEvent()
}
