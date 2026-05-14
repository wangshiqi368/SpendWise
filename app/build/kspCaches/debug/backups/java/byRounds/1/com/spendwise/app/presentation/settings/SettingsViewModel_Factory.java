package com.spendwise.app.presentation.settings;

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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<TransactionUseCases> transactionUseCasesProvider;

  public SettingsViewModel_Factory(Provider<TransactionUseCases> transactionUseCasesProvider) {
    this.transactionUseCasesProvider = transactionUseCasesProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(transactionUseCasesProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<TransactionUseCases> transactionUseCasesProvider) {
    return new SettingsViewModel_Factory(transactionUseCasesProvider);
  }

  public static SettingsViewModel newInstance(TransactionUseCases transactionUseCases) {
    return new SettingsViewModel(transactionUseCases);
  }
}
