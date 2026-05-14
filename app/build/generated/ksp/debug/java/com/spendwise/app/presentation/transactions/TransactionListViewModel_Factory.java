package com.spendwise.app.presentation.transactions;

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
public final class TransactionListViewModel_Factory implements Factory<TransactionListViewModel> {
  private final Provider<TransactionUseCases> transactionUseCasesProvider;

  public TransactionListViewModel_Factory(
      Provider<TransactionUseCases> transactionUseCasesProvider) {
    this.transactionUseCasesProvider = transactionUseCasesProvider;
  }

  @Override
  public TransactionListViewModel get() {
    return newInstance(transactionUseCasesProvider.get());
  }

  public static TransactionListViewModel_Factory create(
      Provider<TransactionUseCases> transactionUseCasesProvider) {
    return new TransactionListViewModel_Factory(transactionUseCasesProvider);
  }

  public static TransactionListViewModel newInstance(TransactionUseCases transactionUseCases) {
    return new TransactionListViewModel(transactionUseCases);
  }
}
