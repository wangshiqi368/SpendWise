package com.spendwise.app.di;

import com.spendwise.app.data.local.BudgetDao;
import com.spendwise.app.domain.repository.BudgetRepository;
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
public final class RepositoryModule_ProvideBudgetRepositoryFactory implements Factory<BudgetRepository> {
  private final Provider<BudgetDao> daoProvider;

  public RepositoryModule_ProvideBudgetRepositoryFactory(Provider<BudgetDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public BudgetRepository get() {
    return provideBudgetRepository(daoProvider.get());
  }

  public static RepositoryModule_ProvideBudgetRepositoryFactory create(
      Provider<BudgetDao> daoProvider) {
    return new RepositoryModule_ProvideBudgetRepositoryFactory(daoProvider);
  }

  public static BudgetRepository provideBudgetRepository(BudgetDao dao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideBudgetRepository(dao));
  }
}
