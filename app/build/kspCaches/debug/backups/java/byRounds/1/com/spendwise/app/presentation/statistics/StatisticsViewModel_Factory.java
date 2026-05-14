package com.spendwise.app.presentation.statistics;

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
public final class StatisticsViewModel_Factory implements Factory<StatisticsViewModel> {
  private final Provider<TransactionUseCases> transactionUseCasesProvider;

  public StatisticsViewModel_Factory(Provider<TransactionUseCases> transactionUseCasesProvider) {
    this.transactionUseCasesProvider = transactionUseCasesProvider;
  }

  @Override
  public StatisticsViewModel get() {
    return newInstance(transactionUseCasesProvider.get());
  }

  public static StatisticsViewModel_Factory create(
      Provider<TransactionUseCases> transactionUseCasesProvider) {
    return new StatisticsViewModel_Factory(transactionUseCasesProvider);
  }

  public static StatisticsViewModel newInstance(TransactionUseCases transactionUseCases) {
    return new StatisticsViewModel(transactionUseCases);
  }
}
