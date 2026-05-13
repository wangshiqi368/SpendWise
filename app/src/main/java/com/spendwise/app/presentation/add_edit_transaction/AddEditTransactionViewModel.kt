package com.spendwise.app.presentation.add_edit_transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendwise.app.domain.model.Transaction
import com.spendwise.app.domain.use_case.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditTransactionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _transactionTitle = mutableStateOf("")
    val transactionTitle: State<String> = _transactionTitle

    private val _transactionAmount = mutableStateOf("")
    val transactionAmount: State<String> = _transactionAmount

    private val _transactionCategory = mutableStateOf("其他")
    val transactionCategory: State<String> = _transactionCategory

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTransactionId: Long? = null

    init {
        savedStateHandle.get<Long>("transactionId")?.let { transactionId ->
            if (transactionId != -1L) {
                viewModelScope.launch {
                    transactionUseCases.getTransaction(transactionId)?.also { transaction ->
                        currentTransactionId = transaction.id
                        _transactionTitle.value = transaction.title
                        _transactionAmount.value = transaction.amount.toString()
                        _transactionCategory.value = transaction.category
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTransactionEvent) {
        when (event) {
            is AddEditTransactionEvent.EnteredTitle -> {
                _transactionTitle.value = event.value
            }
            is AddEditTransactionEvent.EnteredAmount -> {
                _transactionAmount.value = event.value
            }
            is AddEditTransactionEvent.EnteredCategory -> {
                _transactionCategory.value = event.value
            }
            is AddEditTransactionEvent.SaveTransaction -> {
                viewModelScope.launch {
                    try {
                        transactionUseCases.addTransaction(
                            Transaction(
                                title = transactionTitle.value,
                                amount = transactionAmount.value.toDoubleOrNull() ?: 0.0,
                                category = transactionCategory.value,
                                date = LocalDateTime.now(),
                                id = currentTransactionId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTransaction)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "无法保存账单"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveTransaction : UiEvent()
    }
}
