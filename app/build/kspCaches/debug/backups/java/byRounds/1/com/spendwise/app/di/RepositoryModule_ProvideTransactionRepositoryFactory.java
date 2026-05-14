package com.spendwise.app.di;

import com.spendwise.app.data.local.TransactionDao;
import com.spendwise.app.domain.repository.TransactionRepository;
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
public final class RepositoryModule_ProvideTransactionRepositoryFactory implements Factory<TransactionRepository> {
  private final Provider<TransactionDao> daoProvider;

  public RepositoryModule_ProvideTransactionRepositoryFactory(
      Provider<TransactionDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public TransactionRepository get() {
    return provideTransactionRepository(daoProvider.get());
  }

  public static RepositoryModule_ProvideTransactionRepositoryFactory create(
      Provider<TransactionDao> daoProvider) {
    return new RepositoryModule_ProvideTransactionRepositoryFactory(daoProvider);
  }

  public static TransactionRepository provideTransactionRepository(TransactionDao dao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideTransactionRepository(dao));
  }
}
