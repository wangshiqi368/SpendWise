package com.spendwise.app.di;

import com.spendwise.app.data.local.ExchangeRateDao;
import com.spendwise.app.data.remote.ExchangeRateApi;
import com.spendwise.app.domain.repository.ExchangeRateRepository;
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
public final class RepositoryModule_ProvideExchangeRateRepositoryFactory implements Factory<ExchangeRateRepository> {
  private final Provider<ExchangeRateApi> apiProvider;

  private final Provider<ExchangeRateDao> daoProvider;

  public RepositoryModule_ProvideExchangeRateRepositoryFactory(
      Provider<ExchangeRateApi> apiProvider, Provider<ExchangeRateDao> daoProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public ExchangeRateRepository get() {
    return provideExchangeRateRepository(apiProvider.get(), daoProvider.get());
  }

  public static RepositoryModule_ProvideExchangeRateRepositoryFactory create(
      Provider<ExchangeRateApi> apiProvider, Provider<ExchangeRateDao> daoProvider) {
    return new RepositoryModule_ProvideExchangeRateRepositoryFactory(apiProvider, daoProvider);
  }

  public static ExchangeRateRepository provideExchangeRateRepository(ExchangeRateApi api,
      ExchangeRateDao dao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideExchangeRateRepository(api, dao));
  }
}
