package com.spendwise.app.di;

import com.spendwise.app.data.local.AppDatabase;
import com.spendwise.app.data.local.ExchangeRateDao;
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
public final class DatabaseModule_ProvideExchangeRateDaoFactory implements Factory<ExchangeRateDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideExchangeRateDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ExchangeRateDao get() {
    return provideExchangeRateDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideExchangeRateDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideExchangeRateDaoFactory(dbProvider);
  }

  public static ExchangeRateDao provideExchangeRateDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExchangeRateDao(db));
  }
}
