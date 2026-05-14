package com.spendwise.app.di;

import com.spendwise.app.domain.repository.BudgetRepository;
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

  public UseCaseModule_ProvideTransactionUseCasesFactory(
      Provider<TransactionRepository> repositoryProvider,
      Provider<BudgetRepository> budgetRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.budgetRepositoryProvider = budgetRepositoryProvider;
  }

  @Override
  public TransactionUseCases get() {
    return provideTransactionUseCases(repositoryProvider.get(), budgetRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideTransactionUseCasesFactory create(
      Provider<TransactionRepository> repositoryProvider,
      Provider<BudgetRepository> budgetRepositoryProvider) {
    return new UseCaseModule_ProvideTransactionUseCasesFactory(repositoryProvider, budgetRepositoryProvider);
  }

  public static TransactionUseCases provideTransactionUseCases(TransactionRepository repository,
      BudgetRepository budgetRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideTransactionUseCases(repository, budgetRepository));
  }
}
