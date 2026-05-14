package com.spendwise.app.di;

import com.spendwise.app.domain.repository.BudgetRepository;
import com.spendwise.app.domain.repository.ExchangeRateRepository;
import com.spendwise.app.domain.repository.TransactionRepository;
import com.spendwise.app.domain.use_case.TransactionUseCases;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UseCaseModule_ProvideTransactionUseCasesFactory implements Factory<TransactionUseCases> {
  private final Provider<TransactionRepository> repositoryProvider;

  private final Provider<BudgetRepository> budgetRepositoryProvider;

  private final Provider<ExchangeRateRepository> exchangeRateRepositoryProvider;

  public UseCaseModule_ProvideTransactionUseCasesFactory(
      Provider<TransactionRepository> repositoryProvider,
      Provider<BudgetRepository> budgetRepositoryProvider,
      Provider<ExchangeRateRepository> exchangeRateRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.budgetRepositoryProvider = budgetRepositoryProvider;
    this.exchangeRateRepositoryProvider = exchangeRateRepositoryProvider;
  }

  @Override
  public TransactionUseCases get() {
    return provideTransactionUseCases(repositoryProvider.get(), budgetRepositoryProvider.get(), exchangeRateRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideTransactionUseCasesFactory create(
      Provider<TransactionRepository> repositoryProvider,
      Provider<BudgetRepository> budgetRepositoryProvider,
      Provider<ExchangeRateRepository> exchangeRateRepositoryProvider) {
    return new UseCaseModule_ProvideTransactionUseCasesFactory(repositoryProvider, budgetRepositoryProvider, exchangeRateRepositoryProvider);
  }

  public static TransactionUseCases provideTransactionUseCases(TransactionRepository repository,
      BudgetRepository budgetRepository, ExchangeRateRepository exchangeRateRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideTransactionUseCases(repository, budgetRepository, exchangeRateRepository));
  }
}
