package com.spendwise.app.presentation.add_edit_transaction;

import android.app.Application;
import androidx.lifecycle.SavedStateHandle;
import com.spendwise.app.domain.use_case.TransactionUseCases;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AddEditTransactionViewModel_Factory implements Factory<AddEditTransactionViewModel> {
  private final Provider<TransactionUseCases> transactionUseCasesProvider;

  private final Provider<Application> applicationProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public AddEditTransactionViewModel_Factory(
      Provider<TransactionUseCases> transactionUseCasesProvider,
      Provider<Application> applicationProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.transactionUseCasesProvider = transactionUseCasesProvider;
    this.applicationProvider = applicationProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public AddEditTransactionViewModel get() {
    return newInstance(transactionUseCasesProvider.get(), applicationProvider.get(), savedStateHandleProvider.get());
  }

  public static AddEditTransactionViewModel_Factory create(
      Provider<TransactionUseCases> transactionUseCasesProvider,
      Provider<Application> applicationProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new AddEditTransactionViewModel_Factory(transactionUseCasesProvider, applicationProvider, savedStateHandleProvider);
  }

  public static AddEditTransactionViewModel newInstance(TransactionUseCases transactionUseCases,
      Application application, SavedStateHandle savedStateHandle) {
    return new AddEditTransactionViewModel(transactionUseCases, application, savedStateHandle);
  }
}
