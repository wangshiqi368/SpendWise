package com.spendwise.app.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendwise.app.data.util.CsvExporter
import com.spendwise.app.domain.use_case.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ExportToCsv -> {
                viewModelScope.launch {
                    val transactions = transactionUseCases.getTransactions().first()
                    val csvData = CsvExporter.transactionsToCsv(transactions)
                    _eventFlow.emit(UiEvent.ExportCsv(csvData))
                }
            }
            is SettingsEvent.RefreshExchangeRates -> {
                viewModelScope.launch {
                    // Placeholder for now
                    _eventFlow.emit(UiEvent.ShowSnackbar("汇率同步功能即将上线"))
                }
            }
        }
    }

    sealed class UiEvent {
        data class ExportCsv(val csvData: String) : UiEvent()
        data class ShowSnackbar(val message: String) : UiEvent()
    }
}
